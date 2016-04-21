(ns cljs.state)

(def state (atom {:grid/gas-query-grid  [:gas-query-grid/by-id 10800],
                  :gas-query-grid/by-id
                                        {10800
                                         {:id 10800,
                                          :tube/real-gases
                                              [[:gas-at-location/by-id 500]
                                               [:gas-at-location/by-id 501]
                                               [:gas-at-location/by-id 502]
                                               [:gas-at-location/by-id 503]
                                               [:gas-at-location/by-id 504]
                                               [:gas-at-location/by-id 505]
                                               [:gas-at-location/by-id 506]
                                               [:gas-at-location/by-id 507]
                                               [:gas-at-location/by-id 508]
                                               [:gas-at-location/by-id 509]
                                               [:gas-at-location/by-id 510]
                                               [:gas-at-location/by-id 511]
                                               [:gas-at-location/by-id 512]
                                               [:gas-at-location/by-id 513]
                                               [:gas-at-location/by-id 514]
                                               [:gas-at-location/by-id 515]
                                               [:gas-at-location/by-id 516]
                                               [:gas-at-location/by-id 517]
                                               [:gas-at-location/by-id 518]
                                               [:gas-at-location/by-id 519]
                                               [:gas-at-location/by-id 520]
                                               [:gas-at-location/by-id 521]
                                               [:gas-at-location/by-id 522]
                                               [:gas-at-location/by-id 523]
                                               [:gas-at-location/by-id 524]
                                               [:gas-at-location/by-id 525]
                                               [:gas-at-location/by-id 526]
                                               [:gas-at-location/by-id 527]
                                               [:gas-at-location/by-id 528]
                                               [:gas-at-location/by-id 529]
                                               [:gas-at-location/by-id 530]
                                               [:gas-at-location/by-id 531]
                                               [:gas-at-location/by-id 532]
                                               [:gas-at-location/by-id 533]
                                               [:gas-at-location/by-id 534]
                                               [:gas-at-location/by-id 535]
                                               [:gas-at-location/by-id 536]
                                               [:gas-at-location/by-id 537]
                                               [:gas-at-location/by-id 538]
                                               [:gas-at-location/by-id 539]],
                                          :app/sys-gases
                                              [[:gas-of-system/by-id 150]
                                               [:gas-of-system/by-id 151]
                                               [:gas-of-system/by-id 152]
                                               [:gas-of-system/by-id 153]]}},
                  :app/tubes
                                        [[:tube/by-id 1000]
                                         [:tube/by-id 1001]
                                         [:tube/by-id 1002]
                                         [:tube/by-id 1003]
                                         [:tube/by-id 1004]
                                         [:tube/by-id 1005]
                                         [:tube/by-id 1006]
                                         [:tube/by-id 1007]
                                         [:tube/by-id 1008]
                                         [:tube/by-id 1009]],
                  :tube/real-gases
                                        [[:gas-at-location/by-id 500]
                                         [:gas-at-location/by-id 501]
                                         [:gas-at-location/by-id 502]
                                         [:gas-at-location/by-id 503]
                                         [:gas-at-location/by-id 504]
                                         [:gas-at-location/by-id 505]
                                         [:gas-at-location/by-id 506]
                                         [:gas-at-location/by-id 507]
                                         [:gas-at-location/by-id 508]
                                         [:gas-at-location/by-id 509]
                                         [:gas-at-location/by-id 510]
                                         [:gas-at-location/by-id 511]
                                         [:gas-at-location/by-id 512]
                                         [:gas-at-location/by-id 513]
                                         [:gas-at-location/by-id 514]
                                         [:gas-at-location/by-id 515]
                                         [:gas-at-location/by-id 516]
                                         [:gas-at-location/by-id 517]
                                         [:gas-at-location/by-id 518]
                                         [:gas-at-location/by-id 519]
                                         [:gas-at-location/by-id 520]
                                         [:gas-at-location/by-id 521]
                                         [:gas-at-location/by-id 522]
                                         [:gas-at-location/by-id 523]
                                         [:gas-at-location/by-id 524]
                                         [:gas-at-location/by-id 525]
                                         [:gas-at-location/by-id 526]
                                         [:gas-at-location/by-id 527]
                                         [:gas-at-location/by-id 528]
                                         [:gas-at-location/by-id 529]
                                         [:gas-at-location/by-id 530]
                                         [:gas-at-location/by-id 531]
                                         [:gas-at-location/by-id 532]
                                         [:gas-at-location/by-id 533]
                                         [:gas-at-location/by-id 534]
                                         [:gas-at-location/by-id 535]
                                         [:gas-at-location/by-id 536]
                                         [:gas-at-location/by-id 537]
                                         [:gas-at-location/by-id 538]
                                         [:gas-at-location/by-id 539]],
                  :gas-at-location/by-id
                                        {512
                                         {:grid-cell/id 512,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1003]},
                                         513
                                         {:grid-cell/id 513,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1003]},
                                         514
                                         {:grid-cell/id 514,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1003]},
                                         515
                                         {:grid-cell/id 515,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1003]},
                                         516
                                         {:grid-cell/id 516,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1004]},
                                         517
                                         {:grid-cell/id 517,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1004]},
                                         518
                                         {:grid-cell/id 518,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1004]},
                                         519
                                         {:grid-cell/id 519,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1004]},
                                         520
                                         {:grid-cell/id 520,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1005]},
                                         521
                                         {:grid-cell/id 521,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1005]},
                                         522
                                         {:grid-cell/id 522,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1005]},
                                         523
                                         {:grid-cell/id 523,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1005]},
                                         524
                                         {:grid-cell/id 524,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1006]},
                                         525
                                         {:grid-cell/id 525,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1006]},
                                         526
                                         {:grid-cell/id 526,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1006]},
                                         527
                                         {:grid-cell/id 527,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1006]},
                                         528
                                         {:grid-cell/id 528,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1007]},
                                         529
                                         {:grid-cell/id 529,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1007]},
                                         530
                                         {:grid-cell/id 530,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1007]},
                                         531
                                         {:grid-cell/id 531,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1007]},
                                         500
                                         {:grid-cell/id 500,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1000]},
                                         532
                                         {:grid-cell/id 532,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1008]},
                                         501
                                         {:grid-cell/id 501,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1000]},
                                         533
                                         {:grid-cell/id 533,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1008]},
                                         502
                                         {:grid-cell/id 502,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1000]},
                                         534
                                         {:grid-cell/id 534,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1008]},
                                         503
                                         {:grid-cell/id 503,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1000]},
                                         535
                                         {:grid-cell/id 535,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1008]},
                                         504
                                         {:grid-cell/id 504,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1001]},
                                         536
                                         {:grid-cell/id 536,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1009]},
                                         505
                                         {:grid-cell/id 505,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1001]},
                                         537
                                         {:grid-cell/id 537,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1009]},
                                         506
                                         {:grid-cell/id 506,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1001]},
                                         538
                                         {:grid-cell/id 538,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1009]},
                                         507
                                         {:grid-cell/id 507,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1001]},
                                         539
                                         {:grid-cell/id 539,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1009]},
                                         508
                                         {:grid-cell/id 508,
                                          :system-gas   [:gas-of-system/by-id 150],
                                          :tube         [:tube/by-id 1002]},
                                         509
                                         {:grid-cell/id 509,
                                          :system-gas   [:gas-of-system/by-id 151],
                                          :tube         [:tube/by-id 1002]},
                                         510
                                         {:grid-cell/id 510,
                                          :system-gas   [:gas-of-system/by-id 152],
                                          :tube         [:tube/by-id 1002]},
                                         511
                                         {:grid-cell/id 511,
                                          :system-gas   [:gas-of-system/by-id 153],
                                          :tube         [:tube/by-id 1002]}},
                  :ui/locale            "en-US",
                  :app/current-tab      [:app/map :singleton],
                  :om.next/tables
                                        #{:gas-of-system/by-id :tube/by-id :gas-at-location/by-id :line/by-id
                                          :gas-query-grid/by-id
                                          :gas-query-panel/by-id},
                  :line/by-id
                                        {100
                                         {:id        100,
                                          :colour    {:r 255, :g 0, :b 255},
                                          :intersect [:gas-at-location/by-id 500]},
                                         101
                                         {:id        101,
                                          :colour    {:r 0, :g 102, :b 0},
                                          :intersect [:gas-at-location/by-id 501]},
                                         102
                                         {:id        102,
                                          :colour    {:r 0, :g 51, :b 102},
                                          :intersect [:gas-at-location/by-id 502]},
                                         103
                                         {:id        103,
                                          :colour    {:r 255, :g 0, :b 0},
                                          :intersect [:gas-at-location/by-id 503]}},
                  :app/trending
                                        {:singleton
                                         {:id :singleton, :tab/label "Trending", :tab/type :app/trending}},
                  :app/sys-gases
                                        [[:gas-of-system/by-id 150]
                                         [:gas-of-system/by-id 151]
                                         [:gas-of-system/by-id 152]
                                         [:gas-of-system/by-id 153]],
                  :gas-of-system/by-id
                                        {150
                                         {:id         150,
                                          :long-name  "Methane",
                                          :short-name "CH₄",
                                          :lowest     0.25,
                                          :highest    1,
                                          :units      "%"},
                                         151
                                         {:id         151,
                                          :long-name  "Oxygen",
                                          :short-name "O₂",
                                          :lowest     19,
                                          :highest    12,
                                          :units      "%"},
                                         152
                                         {:id         152,
                                          :long-name  "Carbon Monoxide",
                                          :short-name "CO",
                                          :lowest     30,
                                          :highest    55,
                                          :units      "ppm"},
                                         153
                                         {:id         153,
                                          :long-name  "Carbon Dioxide",
                                          :short-name "CO₂",
                                          :lowest     0.5,
                                          :highest    1.35,
                                          :units      "%"}},
                  :app/automatic
                                        {:singleton
                                         {:id :singleton, :tab/label "Automatic", :tab/type :app/automatic}},
                  :app/thresholds
                                        {:singleton
                                         {:id        :singleton,
                                          :tab/label "Thresholds",
                                          :tab/type  :app/thresholds}},
                  :app/reports
                                        {:singleton
                                         {:id :singleton, :tab/label "Reports", :tab/type :app/reports}},
                  :graph/lines
                                        [[:line/by-id 100]
                                         [:line/by-id 101]
                                         [:line/by-id 102]
                                         [:line/by-id 103]],
                  :app/logs
                                        {:singleton {:id :singleton, :tab/label "Logs", :tab/type :app/logs}},
                  :gas-query-panel/by-id
                                        {10700
                                         {:id 10700, :grid/gas-query-grid [:gas-query-grid/by-id 10800]}},
                  :grid/gas-query-panel [:gas-query-panel/by-id 10700],
                  :app/map
                                        {:singleton {:id :singleton, :tab/label "Map", :tab/type :app/map}},
                  :tube/by-id
                                        {1000 {:id 1000, :tube-num 1, :display-name "Tube 1"},
                                         1001 {:id 1001, :tube-num 2, :display-name "Tube 2"},
                                         1002 {:id 1002, :tube-num 3, :display-name "Tube 3"},
                                         1003 {:id 1003, :tube-num 4, :display-name "Tube 4"},
                                         1004 {:id 1004, :tube-num 5, :display-name "Tube 5"},
                                         1005 {:id 1005, :tube-num 6, :display-name "tube6"},
                                         1006 {:id 1006, :tube-num 7, :display-name "tube7"},
                                         1007 {:id 1007, :tube-num 8, :display-name "tube8"},
                                         1008 {:id 1008, :tube-num 9, :display-name "Bag Sampling"},
                                         1009 {:id 1009, :tube-num 10, :display-name "Shed Tube 10"}}})
  )
