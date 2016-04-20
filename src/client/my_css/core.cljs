(ns my-css.core
  (:require my-css.mutations
            [untangled.client.core :as uc]
            [my-css.ui :as ui]
            [my-css.state :as state]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log]
            [cljs.pprint :refer [pprint]])
  (:import goog.History))

(def merged-state (atom (merge state/already-normalized-tabs-state (om/tree->db ui/non-union-part-of-root-query state/initial-state true))))
(pprint @merged-state)

(defonce app (atom (uc/new-untangled-client
                     ; can pass an atom, which means you hand normalized it already.
                     :initial-state merged-state
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


