(ns my-css.components.graphing
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [my-css.components.general :as gen]
            [my-css.components.navigator :as navigator]
            [my-css.components.log-debug :as ld]
            [cljs.pprint :as pp :refer [pprint]]))

(defui Intersect
  static om/Ident
  (ident [this props]
    [:gas-at-location/by-id (:grid-cell/id props)])
  static om/IQuery
  (query [this]
    [:grid-cell/id :value {:tube (om/get-query gen/Location)}
     {:system-gas (om/get-query gen/SystemGas)}]))

(defui Line
       static om/Ident
       (ident [this props]
              [:line/by-id (:id props)])
       static om/IQuery
       (query [this]
              [:id :colour {:intersect (om/get-query Intersect)}]))

(defui TrendingGraph
  static om/Ident
  (ident [this props]
    [:trending-graph/by-id (:id props)])
  static om/IQuery
  (query [this]
    [:id
     :width
     :height
     {:graph/lines (om/get-query Line)}
     {:graph/navigator (om/get-query navigator/GraphNavigator)}
     :labels-visible?
     ;;{:graph/misc (om/get-query Misc)}
     ;;{:graph/plumb-line (om/get-query PlumbLine)}
     {:graph/translators [:point-fn :horiz-fn]}])
  Object
  (render [this]
    (ld/log-render-on "TrendingGraph" this)
    (let [{:keys [width height graph/lines graph/navigator]} (om/props this)
          _ (assert lines)
          lines-count (count lines)
          txt (str "Num lines: " lines-count "\n\n" (pp/write lines :stream nil))]
      (dom/div nil
               (dom/textarea #js{:value txt :style #js{:width width :height height}})
               (navigator/navigator navigator)))))
(def trending-graph-component (om/factory TrendingGraph {:keyfn :id}))
