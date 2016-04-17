(ns my-css.core
  (:require my-css.mutations
            [untangled.client.core :as uc]
            [my-css.ui :as ui]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log])
  (:import goog.History))

(def initial-state {:data-item   {}
                    :main        {:singleton {:id         :singleton
                                              :tab/label  "Main"
                                              :tab/type   :main
                                              :data-items nil}}
                    :settings    {:singleton {:id        :singleton
                                              :tab/label "Settings"
                                              :tab/type  :settings}}
                    ; switch to [:settings :singleton] to change tabs
                    :current-tab [:main :singleton]})

(defonce app (atom (uc/new-untangled-client
                     ; can pass an atom, which means you hand normalized it already.
                     :initial-state (atom initial-state)
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


