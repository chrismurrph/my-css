
Scratchpad for (mainly) css experiments.

## Running it:

Start figwheel (the JVM options tell figwheel which builds to run):

```
JVM_OPTS="-Ddev" lein run -m clojure.main script/figwheel.clj
```

which should start auto-building the cljs source and show a browser REPL.

Navigate to: [http://localhost:2345/dev.html](http://localhost:2345/dev.html)

Changes to the source should re-render without a browser reload.