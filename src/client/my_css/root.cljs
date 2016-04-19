(ns my-css.root
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [my-css.core :as core]
            [om.next :as om]
            [default-db-format.core :as db-format]
            [my-css.ui :as ui]))

;<div class="custom-wrapper pure-g" id="menu">
;<div class="pure-u-1 pure-u-md-1-3">
;<div class="pure-menu">
;<a href="#" class="pure-menu-heading custom-brand">Brand</a>
;<a href="#" class="custom-toggle" id="toggle"><s class="bar"></s><s class="bar"></s></a>
;</div>
;</div>
;<div class="pure-u-1 pure-u-md-1-3">
;<div class="pure-menu pure-menu-horizontal custom-can-transform">
;<ul class="pure-menu-list">
;<li class="pure-menu-item"><a href="#" class="pure-menu-link">Home</a></li>
;<li class="pure-menu-item"><a href="#" class="pure-menu-link">About</a></li>
;<li class="pure-menu-item"><a href="#" class="pure-menu-link">Contact</a></li>
;</ul>
;</div>
;</div>
;<div class="pure-u-1 pure-u-md-1-3">
;<div class="pure-menu pure-menu-horizontal custom-menu-3 custom-can-transform">
;<ul class="pure-menu-list">
;<li class="pure-menu-item"><a href="#" class="pure-menu-link">Yahoo</a></li>
;<li class="pure-menu-item"><a href="#" class="pure-menu-link">W3C</a></li>
;</ul>
;</div>
;</div>
;</div>

(defn tab-style [tab kw]
  #js{:className (str "pure-menu-item" (if (= tab kw) " pure-menu-selected"))})

(defui ^:once App
  static om/IQuery
  (query [this] [:ui/locale :ui/react-key {:current-tab (om/get-query ui/Tab)}])
  Object
  (render [this]
    (let [{:keys [current-tab ui/react-key] :or {ui/react-key "ROOT"} :as props} (om/props this)
          tab (:tab/type current-tab)
          _ (println "tab is " tab)]
      (dom/div #js{:className "custom-wrapper pure-g"
                   :id "menu"}
               (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                        (dom/div #js{:className "pure-menu"}
                                 (dom/a #js{:className "pure-menu-heading custom-brand"
                                            :href "#"} "SMARTGAS")
                                 (dom/a #js{:className "custom-toggle"
                                            :id "toggle"}
                                        (dom/s #js{:className "bar"})
                                        (dom/s #js{:className "bar"}))))
               (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                        (dom/div #js{:className "pure-menu pure-menu-horizontal custom-can-transform"}
                                 (dom/ul #js{:className "pure-menu-list"}
                                         (dom/li (tab-style tab :main)
                                                 (dom/a #js{:className "pure-menu-link"
                                                            :href "#"
                                                            :onClick #(om/transact! this '[(nav/change-tab {:target :main})])} "Main"))
                                         (dom/li (tab-style tab :settings)
                                                 (dom/a #js{:className "pure-menu-link"
                                                            :href "#"
                                                            :onClick #(om/transact! this '[(nav/change-tab {:target :settings})])} "Settings"))
                                         (dom/li #js{:className "pure-menu-item"}
                                                 (dom/a #js{:className "pure-menu-link"
                                                            :href "#"} "Contact")))))
               (dom/div #js{:className "pure-u-1 pure-u-md-1-3"}
                        (dom/div #js{:className "pure-menu pure-menu-horizontal custom-menu-3 custom-can-transform"}
                                 (dom/ul #js{:className "pure-menu-list"}
                                         (dom/li #js{:className "pure-menu-item"}
                                                 (dom/a #js{:className "pure-menu-link"
                                                            :href "#"} "Yahoo"))
                                         (dom/li #js{:className "pure-menu-item"}
                                                 (dom/a #js{:className "pure-menu-link"
                                                            :href "#"} "W3C")))))))))

;;
;; <a class="button button-primary" href="#">Anchor button</a>
;;

;
;
; <div class="flex flex-stretch">
; <div class="flex-auto px2 py4 blue border">
; <h1 class="m0">Hamburger</h1>
; </div>
; <div class="flex blue border">
; <div class="px2 white bg-blue">
; Hot dog
; </div>
; </div>
; </div>
;
;

(comment (defui ^:once Root
           static om/IQuery
           (query [this] [:ui/locale :ui/react-key {:current-tab (om/get-query ui/Tab)}])
           Object
           (render [this]
             (let [{:keys [current-tab ui/react-key] :or {ui/react-key "ROOT"} :as props} (om/props this)
                   tab (:tab/type current-tab)]
               (dom/div nil
                        (dom/div #js{:className "flex"}
                                 (dom/div #js{:className "px2 py4 blue border"}
                                          (dom/h1 #js{:className "m0"} "Hamburger"))
                                 (dom/div #js{:className "flex blue border"}
                                          (dom/div #js{:className "px2 py2 py4 white bg-blue"} "Hot dog")))
                        (dom/button #js{:className "btn btn-primary mb1"} "Anchor button")
                        (dom/div #js{:className "flex"}
                                 (dom/div #js{:className "flex blue border"}
                                          (dom/div #js{:className "p1 blue border"} "One")
                                          (dom/div #js{:className "p1 blue border"} "Two")))
                        (dom/div #js{:key react-key :className "container"} ; needed for forced re-render to work on locale changes and hot reload
                                 (dom/div #js{:className "row"}
                                          (dom/div #js{:className "twelve columns"}
                                                   (dom/nav nil
                                                            (dom/ul nil
                                                                    (dom/li nil #_{:className (str "tab" (if (= tab :main) " active-tab"))}
                                                                            (dom/a #js{:onClick #(om/transact! this '[(nav/change-tab {:target :main})])} "Main"))

                                                                    (dom/li nil #_{:className (str "tab" (if (= tab :settings) " active-tab"))}
                                                                            (dom/a #js{:onClick #(om/transact! this '[(nav/change-tab {:target :settings})])} "Settings")))))
                                          (ui/ui-tab current-tab))))))))

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
