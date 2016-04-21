(ns cljs.mut
  (:require
    [cljs.pprint :refer [pprint]]
    [devtools.core :as devtools]
    [my-css.core :as core]
    my-css.mutations
    [untangled.client.logging :as log]
    [my-css.root :as root]
    [untangled.client.core :as uc]
    [cljs.reader :as reader]
    [cljs.state :as state]))

(enable-console-print!)

(defonce cljs-build-tools
  (do (devtools/enable-feature! :sanity-hints)
      (devtools.core/install!)))

(log/set-level :debug)

(defn- arm-tab [st table k ident]
  (swap! st update-in table assoc k ident))

(println "Use the REPL!")

;(arm-tab state/state [:app/trending :singleton] :grid/gas-query-grid [:gas-query-grid/by-id 10800])

(defn transforms [st]
  (-> st
      (arm-tab [:app/trending :singleton] :grid/gas-query-grid [:gas-query-grid/by-id 10800])))

(defn show []
  (pprint @state/state))

