(ns my-css.ui
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]))

(defui ^:once SettingsTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil
               (dom/h3 nil label)
               (dom/p nil "settings ...")))))

(def ui-settings-tab (om/factory SettingsTab))

(defui ^:once MainTab
  static om/IQuery
  (query [this] [:id :tab/type :tab/label])
  Object
  (render [this]
    (let [{:keys [tab/label]} (om/props this)]
      (dom/div nil #_#js{:className "main"}
               (dom/h3 nil label)
               (dom/p nil "main ...")))))

(def ui-main-tab (om/factory MainTab))

(defui ^:once Tab
  static om/IQuery
  (query [this] {:main (om/get-query MainTab) :settings (om/get-query SettingsTab)})
  static om/Ident
  (ident [this props] [(:tab/type props) (:id props)])
  Object
  (render [this]
    (let [{:keys [tab/type] :as props} (om/props this)]
      (case type
        :main (ui-main-tab props)
        :settings (ui-settings-tab props)
        (dom/div nil "MISSING TAB")))))

(def ui-tab (om/factory Tab))

