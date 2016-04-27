(ns cljs.user
  (:require
    [cljs.pprint :refer [pprint]]
    [devtools.core :as devtools]
    [my-css.core :as core]
    my-css.mutations
    [untangled.client.logging :as log]
    [my-css.root :as root]
    ;[untangled.client.core :as uc]
    [cljs.reader :as reader]
    [my-css.client-core :as muc]))

(enable-console-print!)

(defonce cljs-build-tools
  (do (devtools/enable-feature! :sanity-hints)
      (devtools.core/install!)))

(log/set-level :debug)

(reset! core/app (muc/mount @core/app root/App "app"))

(defn log-app-state
  "Helper for logging the app-state, pass in top-level keywords from the app-state and it will print only those
  keys and their values."
  [& keywords]
  (pprint (let [app-state @(:reconciler @core/app)]
            (if (= 0 (count keywords))
              app-state
              (select-keys app-state keywords)))))

(def tab-loadings [{:target :app/trending
                    :moves  [{:key :grid/gas-query-grid :ident [:gas-query-grid/by-id 10800]}
                             {:key :graph/trending-graph :ident [:trending-graph/by-id 10300]}]}])

(defn unload [state prev-target]
  (let [data (first (filter #(= (:target %) prev-target) tab-loadings))
        ident [prev-target :singleton]
        dissoc-keys (mapv :key (:moves data))]
    (println dissoc-keys)))

(defn load [state new-target]
  (let [data (first (filter #(= (:target %) new-target) tab-loadings))]
    (if data
      (let [ident [new-target :singleton]
            to-assoc (mapcat (fn [item] (vector (:key item) (:ident item))) (:moves data))]
        (println "to-assoc:" to-assoc))
      state)))

(defn test-me []
  (load nil :app/trending))
