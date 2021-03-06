(defproject grendelbot "0.1.0-SNAPSHOT"
  :description "Tweeting John Gardner's Grendel"
  :url "http://twitter.com/worldrimwalker"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [twitter-api "0.7.5"]
                 [environ "0.4.0"]]
  :plugins [[environ/environ.lein "0.2.1"]]
  :main grendelbot.core)
