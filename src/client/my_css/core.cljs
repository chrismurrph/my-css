(ns my-css.core
  (:require my-css.mutations
            [untangled.client.core :as uc]
            [my-css.ui :as ui]
            [my-css.state :as state]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log])
  (:import goog.History))

(defonce app (atom (uc/new-untangled-client
                     ; can pass an atom, which means you hand normalized it already.
                     :initial-state (atom state/initial-state)
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


