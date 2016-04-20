(ns my-css.root
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [my-css.core :as core]
            [om.next :as om]
            [default-db-format.core :as db-format]
            [my-css.ui :as ui]))

(defn tab-style [tab kw]
  #js{:className (str "pure-menu-item" (if (= tab kw) " pure-menu-selected"))})

(defui ^:once App
  static om/IQuery
  (query [this] [:ui/locale :ui/react-key {:current-tab (om/get-query ui/Tab)}])
  Object
  (render [this]
    (let [{:keys [current-tab ui/react-key] :or {ui/react-key "ROOT"} :as props} (om/props this)
          {:keys [tab/type tab/label]} current-tab
          _ (println "tab is " type)]
      (dom/div nil
               (dom/div #js{:className "custom-wrapper pure-g"
                            :id        "menu"}
                        (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                                 (dom/div #js{:className "pure-menu"}
                                          (dom/a #js{:className "pure-menu-heading custom-brand"
                                                     :href      "#"} "SMARTGAS")
                                          (dom/a #js{:className "custom-toggle"
                                                     :id        "toggle"}
                                                 (dom/s #js{:className "bar"})
                                                 (dom/s #js{:className "bar"}))))
                        (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                                 (dom/div #js{:className "pure-menu pure-menu-horizontal custom-can-transform"}
                                          (dom/ul #js{:className "pure-menu-list"}
                                                  (dom/li (tab-style type :map)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :map})])} "Map"))
                                                  (dom/li (tab-style type :trending)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :trending})])} "Trending"))
                                                  (dom/li (tab-style type :thresholds)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :thresholds})])} "Thresholds"))
                                                  (dom/li (tab-style type :reports)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :reports})])} "Reports")))))
                        (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                                 (dom/div #js{:className "pure-menu pure-menu-horizontal custom-menu-3 custom-can-transform"}
                                          (dom/ul #js{:className "pure-menu-list"}
                                                  (dom/li #js{:className "pure-menu-item"}
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"} "Help"))
                                                  #_(dom/li #js{:className "pure-menu-item"}
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"} "W3C"))))))
               (dom/div nil
                        (ui/ui-tab current-tab))
               #_(dom/div #js{:className "main"}
                        (dom/h1 nil "Responsive menu"))))))
