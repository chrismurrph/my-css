(ns my-css.components.navigator
  (:require
    [om.next :as om :refer-macros [defui]]
    [my-css.components.log-debug :as ld]
    [om.dom :as dom]))

(defui GraphNavigator
  static om/Ident
  (ident [this props]
    [:navigator/by-id (:id props)])
  static om/IQuery
  (query [this]
    [:id
     :end-time
     :span-seconds
     :receiving?
     ])
  Object
  (render [this]
    (ld/log-render-on "TrendingGraph" this)
    (let [{:keys [end-time]} (om/props this)]
      (dom/div nil
               (dom/label nil "Start")
               (dom/button #js{:className "button-xlarge pure-button"}
                          (dom/i #js{:className "fa fa-chevron-left"}))
               (dom/button #js{:className "button-xlarge pure-button"}
                           (dom/i #js{:className "fa fa-chevron-right"}))
               (dom/button #js{:className "button-xlarge pure-button"}
                           (dom/i #js{:className "fa fa-caret-square-o-down"}))
               (dom/button #js{:className "button-xlarge pure-button"}
                           (dom/i #js{:className "fa fa-play"}))
               (dom/button #js{:className "button-xlarge pure-button"}
                           (dom/i #js{:className "fa fa-paw"}))
               (dom/label nil "end")))))
(def navigator (om/factory GraphNavigator {:keyfn :id}))
