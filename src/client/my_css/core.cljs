(ns my-css.core
  (:require [untangled.client.core :as uc]
            [my-css.ui :as ui]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log])
  (:import goog.History))

(defonce app (atom (uc/new-untangled-client
                     :initial-state {:ui/current-item :ui/results}
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


