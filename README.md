# Grendelbot

![Grendel image from book cover](https://github.com/danmidwood/grendelbot/raw/master/grendel.jpeg "Grendel")

> I shake my two hairy fists at the sky and I let out a tweet so unspeakable that the water at my feet turns sudden ice and even I myself am left uneasy.

Sit down and enjoy the story of Grendel. One tweet an hour.

Grendel is a classic and probably my all time favorite book. I really recommend picking up a copy.

## Usage

You will need a Twitter application with a generated write-access token.

Set up the environment variables with the information from your Twitter application

```shell
GRENDEL-APP-CONSUMER-KEY=...
GRENDEL-APP-CONSUMER-SECRET=...
GRENDEL-USER-ACCESS-TOKEN=...
GRENDEL-USER-ACCESS-TOKEN-SECRET=...
```

Then run the application through `lein run` and the first tweet will be posted. Run again for the second, again for the third, etc, etc.

```shell
~/repos/grendelbot (master ✘)✭ ᐅ lein run
Compiling grendelbot.core
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Tweeting:  The old ram stands looking down over rockslides, stupidly triumphant.
{:status {:code 200, :msg "OK", :protocol "HTTP/1.1", :major 1, <snip lots of Twitter information>
```


## License

Copyright © 2013 Dan Midwood

Distributed under the Eclipse Public License, the same as Clojure.
