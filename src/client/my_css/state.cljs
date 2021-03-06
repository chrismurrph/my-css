(ns my-css.state
  (:require [cljs-time.core :as time]))

(def blue {:r 0 :g 51 :b 102})
(def pink {:r 255 :g 0 :b 255})
(def green {:r 0 :g 102 :b 0})
(def red {:r 255 :g 0 :b 0})

(def irrelevant-keys #{:app/map
                       :app/trending
                       :app/thresholds
                       :app/reports
                       :app/automatic
                       :app/logs
                       :app/current-tab
                       :ui/react-key
                       :ui/locale
                       :om.next/tables
                       })
(def okay-val-maps #{[:r :g :b]
                     [:horiz-fn :vert-fn :point-fn]})
(def check-config {:excluded-keys   irrelevant-keys
                   :okay-value-maps okay-val-maps
                   :by-id-kw        "by-id"
                   })

;;
;; Because these are unionised tree->db (and thus auto normalization) does not work
;; Hence they are hand-normalized here, and must be separate
;; As separate you can't include anything in them that would be needed for normalization
;; So the :app/trending component cannot subsume the :graph/trending-graph component.
;; Thus in the tab components we must just pass the props through again.
;;
(def already-normalized-tabs-state
  {:app/map         {:singleton {:id        :singleton
                                 :tab/label "Map"
                                 :tab/type  :app/map}}
   :app/trending    {:singleton {:id        :singleton
                                 :tab/label "Trending"
                                 :tab/type  :app/trending
                                 ; These will be dynamically transferred in post-load
                                 ; (doesn't seem to hurt having)
                                 :grid/gas-query-grid {:id 10800}
                                 :graph/trending-graph {:id 10300}
                                 }}
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

(def initial-state {:grid/gas-query-panel
                                         {:id                   10700
                                          :grid/gas-query-grid  {:id 10800}
                                          :graph/trending-graph {:id 10300}
                                          }

                    :graph/trending-graph
                                         {:id                10300
                                          :width             640
                                          :height            250
                                          :graph/navigator   {:id 10600}
                                          :graph/lines       [{:id 100} {:id 101} {:id 102} {:id 103}]
                                          :labels-visible?   false
                                          ;;:graph/plumb-line  {:id 10201}
                                          :graph/translators {:horiz-fn nil :vert-fn nil :point-fn nil}
                                          ;;:graph/misc        {:id 10400}
                                          :hover-pos         nil
                                          :last-mouse-moment nil
                                          }

                    :grid/gas-query-grid {:id              10800
                                          :tube/real-gases [{:grid-cell/id 500} {:grid-cell/id 501} {:grid-cell/id 502} {:grid-cell/id 503}
                                                            {:grid-cell/id 504} {:grid-cell/id 505} {:grid-cell/id 506} {:grid-cell/id 507}
                                                            {:grid-cell/id 508} {:grid-cell/id 509} {:grid-cell/id 510} {:grid-cell/id 511}
                                                            {:grid-cell/id 512} {:grid-cell/id 513} {:grid-cell/id 514} {:grid-cell/id 515}
                                                            {:grid-cell/id 516} {:grid-cell/id 517} {:grid-cell/id 518} {:grid-cell/id 519}
                                                            {:grid-cell/id 520} {:grid-cell/id 521} {:grid-cell/id 522} {:grid-cell/id 523}
                                                            {:grid-cell/id 524} {:grid-cell/id 525} {:grid-cell/id 526} {:grid-cell/id 527}
                                                            {:grid-cell/id 528} {:grid-cell/id 529} {:grid-cell/id 530} {:grid-cell/id 531}
                                                            {:grid-cell/id 532} {:grid-cell/id 533} {:grid-cell/id 534} {:grid-cell/id 535}
                                                            {:grid-cell/id 536} {:grid-cell/id 537} {:grid-cell/id 538} {:grid-cell/id 539}
                                                            ]
                                          :app/sys-gases   [{:id 150} {:id 151} {:id 152} {:id 153}]}
                    :graph/lines
                                         [
                                          {:id        100
                                           :colour    pink
                                           :intersect {:grid-cell/id 500}}
                                          {:id        101
                                           :colour    green
                                           :intersect {:grid-cell/id 501}}
                                          {:id        102
                                           :colour    blue
                                           :intersect {:grid-cell/id 502}}
                                          {:id        103
                                           :colour    red
                                           :intersect {:grid-cell/id 503}}
                                          ]

                    :graph/navigator
                                         {:id           10600
                                          :end-time     (time/now)
                                          :span-seconds (* 60 60)
                                          :receiving?   false
                                          }

                    :app/sys-gases       [{:id     150 :long-name "Methane" :short-name "CH\u2084"
                                           :lowest 0.25 :highest 1 :units "%"}
                                          {:id     151 :long-name "Oxygen" :short-name "O\u2082"
                                           :lowest 19 :highest 12 :units "%"}
                                          {:id     152 :long-name "Carbon Monoxide" :short-name "CO"
                                           :lowest 30 :highest 55 :units "ppm"}
                                          {:id     153 :long-name "Carbon Dioxide" :short-name "CO\u2082"
                                           :lowest 0.5 :highest 1.35 :units "%"}]
                    :app/tubes
                                         [{:id           1000
                                           :tube-num     1
                                           :display-name "Tube 1"}
                                          {:id           1001
                                           :tube-num     2
                                           :display-name "Tube 2"}
                                          {:id           1002
                                           :tube-num     3
                                           :display-name "Tube 3"}
                                          {:id           1003
                                           :tube-num     4
                                           :display-name "Tube 4"}
                                          {:id           1004
                                           :tube-num     5
                                           :display-name "Tube 5"}
                                          {:id           1005
                                           :tube-num     6
                                           :display-name "tube6"}
                                          {:id           1006
                                           :tube-num     7
                                           :display-name "tube7"}
                                          {:id           1007
                                           :tube-num     8
                                           :display-name "tube8"}
                                          {:id           1008
                                           :tube-num     9
                                           :display-name "Bag Sampling"} ;; could be "Calibrating" - doesn't matter for now
                                          {:id           1009
                                           :tube-num     10
                                           :display-name "Shed Tube 10"}
                                          ]
                    :tube/real-gases
                                         [{:grid-cell/id 500
                                           :system-gas   {:id 150}
                                           :tube         {:id 1000}
                                           :selected?    true}
                                          {:grid-cell/id 501
                                           :system-gas   {:id 151}
                                           :tube         {:id 1000}
                                           :selected?    true}
                                          {:grid-cell/id 502
                                           :system-gas   {:id 152}
                                           :tube         {:id 1000}
                                           :selected?    true}
                                          {:grid-cell/id 503
                                           :system-gas   {:id 153}
                                           :tube         {:id 1000}
                                           :selected?    true}
                                          {:grid-cell/id 504
                                           :system-gas   {:id 150}
                                           :tube         {:id 1001}
                                           :selected?    false}
                                          {:grid-cell/id 505
                                           :system-gas   {:id 151}
                                           :tube         {:id 1001}
                                           :selected?    false}
                                          {:grid-cell/id 506
                                           :system-gas   {:id 152}
                                           :tube         {:id 1001}
                                           :selected?    false}
                                          {:grid-cell/id 507
                                           :system-gas   {:id 153}
                                           :tube         {:id 1001}
                                           :selected?    false}
                                          {:grid-cell/id 508
                                           :system-gas   {:id 150}
                                           :tube         {:id 1002}
                                           :selected?    false}
                                          {:grid-cell/id 509
                                           :system-gas   {:id 151}
                                           :tube         {:id 1002}
                                           :selected?    false}
                                          {:grid-cell/id 510
                                           :system-gas   {:id 152}
                                           :tube         {:id 1002}
                                           :selected?    false}
                                          {:grid-cell/id 511
                                           :system-gas   {:id 153}
                                           :tube         {:id 1002}
                                           :selected?    false}
                                          {:grid-cell/id 512
                                           :system-gas   {:id 150}
                                           :tube         {:id 1003}
                                           :selected?    false}
                                          {:grid-cell/id 513
                                           :system-gas   {:id 151}
                                           :tube         {:id 1003}
                                           :selected?    false}
                                          {:grid-cell/id 514
                                           :system-gas   {:id 152}
                                           :tube         {:id 1003}
                                           :selected?    false}
                                          {:grid-cell/id 515
                                           :system-gas   {:id 153}
                                           :tube         {:id 1003}
                                           :selected?    false}
                                          {:grid-cell/id 516
                                           :system-gas   {:id 150}
                                           :tube         {:id 1004}
                                           :selected?    false}
                                          {:grid-cell/id 517
                                           :system-gas   {:id 151}
                                           :tube         {:id 1004}
                                           :selected?    false}
                                          {:grid-cell/id 518
                                           :system-gas   {:id 152}
                                           :tube         {:id 1004}
                                           :selected?    false}
                                          {:grid-cell/id 519
                                           :system-gas   {:id 153}
                                           :tube         {:id 1004}
                                           :selected?    false}
                                          {:grid-cell/id 520
                                           :system-gas   {:id 150}
                                           :tube         {:id 1005}
                                           :selected?    false}
                                          {:grid-cell/id 521
                                           :system-gas   {:id 151}
                                           :tube         {:id 1005}
                                           :selected?    false}
                                          {:grid-cell/id 522
                                           :system-gas   {:id 152}
                                           :tube         {:id 1005}
                                           :selected?    false}
                                          {:grid-cell/id 523
                                           :system-gas   {:id 153}
                                           :tube         {:id 1005}
                                           :selected?    false}
                                          {:grid-cell/id 524
                                           :system-gas   {:id 150}
                                           :tube         {:id 1006}
                                           :selected?    false}
                                          {:grid-cell/id 525
                                           :system-gas   {:id 151}
                                           :tube         {:id 1006}
                                           :selected?    false}
                                          {:grid-cell/id 526
                                           :system-gas   {:id 152}
                                           :tube         {:id 1006}
                                           :selected?    false}
                                          {:grid-cell/id 527
                                           :system-gas   {:id 153}
                                           :tube         {:id 1006}
                                           :selected?    false}
                                          {:grid-cell/id 528
                                           :system-gas   {:id 150}
                                           :tube         {:id 1007}
                                           :selected?    false}
                                          {:grid-cell/id 529
                                           :system-gas   {:id 151}
                                           :tube         {:id 1007}
                                           :selected?    false}
                                          {:grid-cell/id 530
                                           :system-gas   {:id 152}
                                           :tube         {:id 1007}
                                           :selected?    false}
                                          {:grid-cell/id 531
                                           :system-gas   {:id 153}
                                           :tube         {:id 1007}
                                           :selected?    false}
                                          {:grid-cell/id 532
                                           :system-gas   {:id 150}
                                           :tube         {:id 1008}
                                           :selected?    false}
                                          {:grid-cell/id 533
                                           :system-gas   {:id 151}
                                           :tube         {:id 1008}
                                           :selected?    false}
                                          {:grid-cell/id 534
                                           :system-gas   {:id 152}
                                           :tube         {:id 1008}
                                           :selected?    false}
                                          {:grid-cell/id 535
                                           :system-gas   {:id 153}
                                           :tube         {:id 1008}
                                           :selected?    false}
                                          {:grid-cell/id 536
                                           :system-gas   {:id 150}
                                           :tube         {:id 1009}
                                           :selected?    false}
                                          {:grid-cell/id 537
                                           :system-gas   {:id 151}
                                           :tube         {:id 1009}
                                           :selected?    false}
                                          {:grid-cell/id 538
                                           :system-gas   {:id 152}
                                           :tube         {:id 1009}
                                           :selected?    false}
                                          {:grid-cell/id 539
                                           :system-gas   {:id 153}
                                           :tube         {:id 1009}
                                           :selected?    false}
                                          ]
                    })
