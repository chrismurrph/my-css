(ns my-css.core
  (:require my-css.mutations
            ;[untangled.client.core :as uc]
            [my-css.client-core :as muc]
            [my-css.ui :as ui]
            [my-css.state :as state]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log]
            [cljs.pprint :refer [pprint]])
  (:import goog.History))

(def merged-state (atom (merge state/already-normalized-tabs-state (om/tree->db ui/non-union-part-of-root-query state/initial-state true))))
(println "merged-state has :app/current-tab :- " (:app/current-tab @merged-state))

(defn- arm-tab [st table k ident]
  (swap! st update-in table assoc k ident))

(defn transforms [st]
  (-> st
      (arm-tab [:app/trending :singleton] :grid/gas-query-grid [:gas-query-grid/by-id 10800])))

(defmulti read om/dispatch)
(defmulti mutate om/dispatch)

(defmethod read :default
  [{:keys [state query]} key _]
  (let [st @state]
    {:value (om/db->tree query (get st key) st)}))

(def my-parser
  (om/parser {:read read
              :mutate mutate}))

(defonce app (atom (muc/new-not-untangled-client
                     ; passing an atom, since have hand normalized it already.
                     :initial-state merged-state
                     :my-parser my-parser
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


