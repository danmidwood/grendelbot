(ns grendelbot.core
  (:require [twitter.oauth :as toauth]
            [twitter.api.restful :as twitter]
            [environ.core :refer [env]]))

(def my-creds (toauth/make-oauth-creds (env :grendel-app-consumer-key)
                                       (env :grendel-app-consumer-secret)
                                       (env :grendel-user-access-token)
                                       (env :grendel-user-access-token-secret)))

(defn ^:private trim-lines [lines]
  (map clojure.string/trim lines))

(defn split-tweet [content]
  (if (< (count content) 140)
    (list content)
    (let [first-tweet (subs content 0 120)
          second-part (subs content 120)
          [first-end second-part] (clojure.string/split second-part #" " 2)
          tweet (clojure.string/join [first-tweet first-end])]
      (conj (split-tweet second-part) tweet))))

(defn shorten-long-tweets [contents]
  (flatten (map split-tweet contents)))


(defn ^:private combine-short-lines
  ([text] (combine-short-lines text nil nil))
  ([text output-text]
     (if-let [next (first text)]
       (if (< 50 (count next))
         (combine-short-lines (rest text) (conj output-text next))
         (combine-short-lines (rest text) next output-text))
       output-text)
     )
  ([text acc output-text]
     (if-let [next (first text)]
       (let [joined (clojure.string/join " " [acc next])]
         (if (> (count joined) 140)
           (combine-short-lines text (conj output-text acc))
           (if (< 50 (count next))
             (combine-short-lines (rest text)
                                  (conj output-text
                                        joined))
             (combine-short-lines (rest text)
                                  joined
                                  output-text))))
       (conj output-text acc))))


(defn ^:private load-content []
  (->
   (slurp (clojure.java.io/resource "grendel.txt"))
   (clojure.string/replace #"\n" " ")
   (clojure.string/join)
   (clojure.string/replace #" +" " ")
   (clojure.string/split #"(?<=[.!?].)")
   combine-short-lines
   reverse
   trim-lines
   shorten-long-tweets))

(def tweets (cycle (load-content)))

(defn tweet [content]
  (println "Tweeting: " content)
  (twitter/statuses-update :oauth-creds my-creds
                               :params {:status content}))


(defn get-last-tweet []
  (->  (twitter/statuses-user-timeline :oauth-creds my-creds
                                       :params {:count 1})
       :body
       first
       :text))

(defn -main []
  (if-let [last-tweet (get-last-tweet)]
      (->> (drop-while (comp not (partial = last-tweet)) tweets)
          (drop 1)
          first
          tweet)
      (tweet (first tweets))))
