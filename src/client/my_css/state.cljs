(ns my-css.state)

(def initial-state {:app/data-item   {}
                    :app/map         {:singleton {:id         :singleton
                                                  :tab/label  "Map"
                                                  :tab/type   :app/map
                                                  :data-items nil}}
                    :app/trending    {:singleton {:id        :singleton
                                                  :tab/label "Trending"
                                                  :tab/type  :app/trending}}
                    :app/thresholds  {:singleton {:id        :singleton
                                                  :tab/label "Thresholds"
                                                  :tab/type  :app/thresholds}}
                    :app/reports     {:singleton {:id        :singleton
                                                  :tab/label "Reports"
                                                  :tab/type  :app/reports}}
                    :app/automatic   {:singleton {:id        :singleton
                                                  :tab/label "Automatic"
                                                  :tab/type  :app/automatic}}
                    :app/logs        {:singleton {:id        :singleton
                                                  :tab/label "Logs"
                                                  :tab/type  :app/logs}}
                    ; switch to [:settings :singleton] to change tabs
                    :app/current-tab [:app/map :singleton]})
