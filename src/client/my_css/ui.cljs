(ns my-css.ui
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]))

(defui ^:once MapTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil #_#js{:className "main"}
               (dom/h3 nil label)
               (dom/p nil "map ...")))))
(def ui-map-tab (om/factory MapTab))

(defui ^:once TrendingTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil
               (dom/h3 nil label)
               (dom/p nil "trending ...")))))
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
  (query [this] {:app/map (om/get-query MapTab)
                 :app/trending (om/get-query TrendingTab)
                 :app/thresholds (om/get-query ThresholdsTab)
                 :app/reports (om/get-query ReportsTab)})
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

