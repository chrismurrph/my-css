(ns my-css.root
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [my-css.core :as core]
            [om.next :as om]
            [default-db-format.core :as db-format]
            [my-css.ui :as ui]))

;;
;; <a class="button button-primary" href="#">Anchor button</a>
;;

(defn tab-style [tab]
  #js{:className (str "one column button" (if (= tab :main) " active-tab"))})

(defui ^:once Root
  static om/IQuery
  (query [this] [:ui/locale :ui/react-key {:current-tab (om/get-query ui/Tab)}])
  Object
  (render [this]
    (let [{:keys [current-tab ui/react-key] :or {ui/react-key "ROOT"} :as props} (om/props this)
          tab (:tab/type current-tab)]
      (dom/div nil
               (dom/a #js{:className "button button-primary"} "Anchor button")
               (dom/div #js{:className "container"}
                        (dom/div #js{:className "row"}
                                 (dom/div #js{:className "one column button active-tab"} "One")
                                 (dom/div #js{:className "one column button button-primary"} "Two")))
               (dom/div #js{:key react-key :className "container"} ; needed for forced re-render to work on locale changes and hot reload
                        (dom/div #js{:className "row"}
                                 (dom/div #js{:className "twelve columns"}
                                          (dom/nav nil
                                                   (dom/ul nil
                                                           (dom/li nil #_{:className (str "tab" (if (= tab :main) " active-tab"))}
                                                                   (dom/a #js{:onClick #(om/transact! this '[(nav/change-tab {:target :main})])} "Main"))

                                                           (dom/li nil #_{:className (str "tab" (if (= tab :settings) " active-tab"))}
                                                                   (dom/a #js{:onClick #(om/transact! this '[(nav/change-tab {:target :settings})])} "Settings")))))
                                 (ui/ui-tab current-tab)))))))

#_(defui ^:once Root
       static om/IQuery
       (query [_]
              [{:root-join (om/get-query ui/AutoCompleter)}])
       Object
       (render [this]
               (let [app-props (om/props this)
                     {:keys [root-join]} app-props
                     my-reconciler (:reconciler @core/app)]
                 (dom/div nil
                          (if my-reconciler
                            (db-format/show-hud (db-format/check my-reconciler))
                            (println "reconciler not available in Root component"))
                          (ui/auto-completer root-join)
                          (dom/br nil)))))
