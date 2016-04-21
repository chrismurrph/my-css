(ns my-css.ui
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [my-css.components.graphing :as graph]
            [my-css.components.grid :as grid]
            [my-css.components.general :as gen]))

(def non-union-part-of-root-query
  [{:app/sys-gases (om/get-query gen/SystemGas)}
   {:app/tubes (om/get-query gen/Location)}
   {:tube/real-gases (om/get-query grid/GridDataCell)}
   {:graph/lines (om/get-query graph/Line)}])

(defui ^:once MapTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil
               (dom/h3 nil label)
               (dom/p nil "map ...")))))
(def ui-map-tab (om/factory MapTab))

;<table class="pure-table">
;<thead>
;<tr>
;<th>#</th>
;<th>Make</th>
;<th>Model</th>
;<th>Year</th>
;</tr>
;</thead>
;
;<tbody>
;<tr>
;<td>1</td>
;<td>Honda</td>
;<td>Accord</td>
;<td>2009</td>
;</tr>
;
;<tr>
;<td>2</td>
;<td>Toyota</td>
;<td>Camry</td>
;<td>2012</td>
;</tr>
;
;<tr>
;<td>3</td>
;<td>Hyundai</td>
;<td>Elantra</td>
;<td>2010</td>
;</tr>
;</tbody>
;</table>
(defui ^:once TrendingTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/table #js{:className "pure-table pure-table-striped"}
                 (dom/thead nil
                            (dom/tr nil
                                    (dom/th nil "Tube #")
                                    (dom/th nil "CH4")
                                    (dom/th nil "O2")
                                    (dom/th nil "CO")
                                    (dom/th nil "CO2")))
                 (dom/tbody nil
                            (dom/tr nil
                                    (dom/td nil "1")
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"})))
                            (dom/tr nil
                                    (dom/td nil "2")
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"}))
                                    (dom/td nil (dom/input #js{:type "checkbox"}))))))))
(def ui-trending-tab (om/factory TrendingTab))

(defui ^:once ThresholdsTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil
               (dom/h3 nil label)
               (dom/p nil "thresholds ...")))))
(def ui-thresholds-tab (om/factory ThresholdsTab))

(defui ^:once ReportsTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil
               (dom/h3 nil label)
               (dom/p nil "reports ...")))))
(def ui-reports-tab (om/factory ReportsTab))

(defui ^:once TabUnion
  static om/IQuery
  (query [this] {:app/map        (om/get-query MapTab)
                 :app/trending   (om/get-query TrendingTab)
                 :app/thresholds (om/get-query ThresholdsTab)
                 :app/reports    (om/get-query ReportsTab)})
  static om/Ident
  (ident [this props] [(:tab/type props) (:id props)])
  Object
  (render [this]
    (let [{:keys [tab/type] :as props} (om/props this)
          _ (assert type)]
      (case type
        :app/map (ui-map-tab props)
        :app/trending (ui-trending-tab props)
        :app/thresholds (ui-thresholds-tab props)
        :app/reports (ui-reports-tab props)
        (dom/div nil (str "MISSING TAB: <" type ">"))))))

(def ui-tab (om/factory TabUnion))

