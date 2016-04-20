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
                    :map         {:singleton {:id         :singleton
                                              :tab/label  "Map"
                                              :tab/type   :map
                                              :data-items nil}}
                    :trending    {:singleton {:id        :singleton
                                              :tab/label "Trending"
                                              :tab/type  :trending}}
                    :thresholds  {:singleton {:id        :singleton
                                              :tab/label "Thresholds"
                                              :tab/type  :thresholds}}
                    :reports     {:singleton {:id        :singleton
                                              :tab/label "Reports"
                                              :tab/type  :reports}}
                    :automatic   {:singleton {:id        :singleton
                                              :tab/label "Automatic"
                                              :tab/type  :automatic}}
                    :logs        {:singleton {:id        :singleton
                                              :tab/label "Logs"
                                              :tab/type  :logs}}
                    ; switch to [:settings :singleton] to change tabs
                    :current-tab [:map :singleton]})

(defonce app (atom (uc/new-untangled-client
                     ; can pass an atom, which means you hand normalized it already.
                     :initial-state (atom initial-state)
                     :started-callback (fn [app]
                                         #_(configure-routing! (:reconciler app))
                                         #_(let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


