(ns my-css.root
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [my-css.core :as core]
            [om.next :as om]
            [default-db-format.core :as db-format]
            [cljs.pprint :as pp :refer [pprint]]
            [my-css.ui :as ui]))

(defn tab-style [tab kw]
  #js{:className (str "pure-menu-item" (if (= tab kw) " pure-menu-selected"))})

(defn check-default-db [st]
  (let [_ (assert (not (nil? st)))
        version db-format/version
        check-result (db-format/check st)
        ok? (db-format/ok? check-result)
        msg-boiler (str "normalized (default-db-format ver: " version ")")
        message (if ok?
                  (str "GOOD: state fully " msg-boiler)
                  (str "BAD: state not fully " msg-boiler))]
    (println message)
    (when (not ok?)
      ;(pprint check-result)
      (pprint st)
      (db-format/show-hud check-result))))

(defui ^:once App
  static om/IQuery
  (query [this] [:ui/locale :ui/react-key {:app/current-tab (om/get-query ui/Tab)}])
  Object
  (render [this]
    (let [{:keys [app/current-tab ui/react-key] :or {ui/react-key "ROOT"} :as props} (om/props this)
          {:keys [tab/type tab/label]} current-tab
          _ (println "tab is " type " from " current-tab)
          my-reconciler (:reconciler @core/app)]
      (dom/div nil
               (if my-reconciler
                 (db-format/show-hud (db-format/check @my-reconciler))
                 (println "reconciler not available in Root component when first mounted"))
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
                                                  (dom/li (tab-style type :app/map)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :app/map})])} "Map"))
                                                  (dom/li (tab-style type :app/trending)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :app/trending})])} "Trending"))
                                                  (dom/li (tab-style type :app/thresholds)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :app/thresholds})])} "Thresholds"))
                                                  (dom/li (tab-style type :app/reports)
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick   #(om/transact! this '[(nav/change-tab {:target :app/reports})])} "Reports")))))
                        (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                                 (dom/div #js{:className "pure-menu pure-menu-horizontal custom-menu-3 custom-can-transform"}
                                          (dom/ul #js{:className "pure-menu-list"}
                                                  (dom/li #js{:className "pure-menu-item"}
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"
                                                                     :onClick #(pprint @(:reconciler @core/app))} "Help"))
                                                  #_(dom/li #js{:className "pure-menu-item"}
                                                          (dom/a #js{:className "pure-menu-link"
                                                                     :href      "#"} "W3C"))))))
               (dom/div nil
                        (ui/ui-tab current-tab))
               #_(dom/div #js{:className "main"}
                        (dom/h1 nil "Responsive menu"))))))
