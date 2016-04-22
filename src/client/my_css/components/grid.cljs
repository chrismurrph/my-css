(ns my-css.components.grid
  (:require [om.next :as om :refer-macros [defui]]
            [my-css.components.general :as gen]
            [om.dom :as dom]
            [my-css.components.log-debug :as ld]
            [my-css.components.graphing :as graph]))

(defui GridDataCell
  static om/Ident
  (ident [this props]
    [:gas-at-location/by-id (:grid-cell/id props)])
  static om/IQuery
  (query [this]
    [:grid-cell/id
     :selected?
     {:system-gas (om/get-query gen/SystemGas)}
     {:tube (om/get-query gen/Location)}])
  Object
  (pick [this click-cb-fn id selected?]
    (assert id)
    (assert (not (nil? selected?)))
    (click-cb-fn id selected?))
  (render [this]
    (ld/log-render "GridDataCell" this :grid-cell/id)
    (let [{:keys [grid-cell/id system-gas tube selected?] :as props} (om/props this)
          _ (assert id)
          {:keys [tube-num sui-col-info click-cb-fn]} (om/get-computed this)
          _ (assert sui-col-info)
          _ (assert click-cb-fn "GridDataCell")
          ]
      (if system-gas
        (dom/div sui-col-info
                 (dom/div #js {:className (str "ui" (if selected? " checked " " ") "checkbox")}
                          (dom/input #js {:type    "checkbox"
                                          :checked (boolean selected?) ;; <- Note boolean function - js needs it!
                                          :onClick (fn [_] (.pick this click-cb-fn id selected?))})
                          (dom/label nil ""))
                 )
        (dom/div sui-col-info
                 (dom/label nil tube-num))))))
(def grid-data-cell-component (om/factory GridDataCell {:keyfn :grid-cell/id}))

(defn selected?
  "Is the gas, which is really gas at location (intersect), one of the ones that there's a line for?"
  [gas line-intersect-ids]
  (let [intersect-id (:grid-cell/id gas) ;; a gas id is the same as an intersect id
        res (some #{intersect-id} line-intersect-ids)
        ;_ (println "Going thru " line-intersect-ids " looking for intersect id " intersect-id " got " res)
        ]
    res))

;;
;; Another way of looking at a gas but don't need an ident as already have with SystemGas
;;
(defui GridHeaderLabel
  static om/IQuery
  (query [this]
    [:id
     :short-name])
  Object
  (render [this]
    (ld/log-render "GridHeaderLabel" this)
    (let [props (om/props this)
          {:keys [short-name]} props
          {:keys [sui-col-info]} (om/get-computed this)
          ;_ (println "GAS:" name)
          ]
      (dom/div sui-col-info
               (dom/label nil short-name)))))
(def grid-header-label (om/factory GridHeaderLabel {:keyfn :id}))

(defui GridHeaderRow
  Object
  (render [this]
    (ld/log-render "GridHeaderRow" this)
    (let [sys-gases (:app/sys-gases (om/props this))
          hdr-gases (into [{:id 0 :short-name "Tube"}] sys-gases)]
      (dom/div #js {:className "row"}
               (map #(grid-header-label (om/computed % (om/get-computed this))) hdr-gases)))))
(def grid-header-row (om/factory GridHeaderRow {:keyfn (fn [_] "GridHeaderRow")}))

#_(defn grid-body-row [map-fn hdr-and-gases]
    (dom/div #js {:className "row"}
             (map map-fn hdr-and-gases)))

;;
;; One we are completely making up, for React's benefit
;;
(defui GridBodyRow
  Object
  (render [this]
    (ld/log-render "GridBodyRow" this)
    (let [{:keys [id real-gases]} (om/props this)
          _ (assert id)
          _ (assert real-gases)
          {:keys [sui-col-info click-cb-fn]} (om/get-computed this)
          _ (assert (and sui-col-info click-cb-fn))]
      (dom/div #js {:className "row"}
               (for [gas real-gases]
                 (grid-data-cell-component
                   (om/computed gas {:sui-col-info sui-col-info
                                     :tube-num    (-> gas :hdr-tube-num)
                                     :click-cb-fn click-cb-fn})))))))
(def grid-body-row (om/factory GridBodyRow {:keyfn :id}))

(defui GasQueryGrid
  static om/Ident
  (ident [this props]
    [:gas-query-grid/by-id (:id props)])
  static om/IQuery
  (query [this]
    [:id
     {:tube/real-gases (om/get-query GridDataCell)}
     {:app/sys-gases (om/get-query gen/SystemGas)}
     ])
  Object
  (render [this]
    (ld/log-render-on "GasQueryGrid" this)
    (let [props (om/props this)
          {:keys [id tube/real-gases app/sys-gases]} props
          _ (assert id)
          _ (assert real-gases "no real-gases inside GasQueryGrid")
          _ (println "FIRST:" (first real-gases))
          ;_ (pprint real-gases)
          {:keys [sui-col-info click-cb-fn]} (om/get-computed this)
          sui-col-info-map {:sui-col-info sui-col-info}
          _ (assert sui-col-info)
          sui-grid-info #js {:className "ui column grid"}
          all-hdr-and-gases (map-indexed #(into [{:grid-cell/id 0 :hdr-tube-num (inc %1)}] %2) (partition (count sys-gases) real-gases))]
      (dom/div sui-grid-info
               (grid-header-row (om/computed props sui-col-info-map))
               (for [hdr-and-gases all-hdr-and-gases]
                 (grid-body-row (om/computed {:real-gases hdr-and-gases
                                              :id         (:hdr-tube-num (first hdr-and-gases))}
                                             (merge sui-col-info-map
                                                    {:click-cb-fn click-cb-fn}))))))))
(def gas-query-grid-component (om/factory GasQueryGrid {:keyfn :id}))

(defui GasQueryPanel
  static om/Ident
  (ident [this props]
    [:gas-query-panel/by-id (:id props)])
  static om/IQuery
  (query [this]
    [:id
     {:grid/gas-query-grid (om/get-query GasQueryGrid)}
     ;{:graph/trending-graph (om/get-query graph/TrendingGraph)}
     ]))
(def gas-query-panel-component (om/factory GasQueryPanel {:keyfn :id}))

(comment (dom/table #js{:className "pure-table pure-table-striped"}
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
                                       (dom/td nil (dom/input #js{:type "checkbox"}))))))

(comment )
