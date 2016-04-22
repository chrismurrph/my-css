(ns my-css.components.graph
  (:require [om.next :as om :refer-macros [defui]]
            [my-css.components.general :as gen]))

;;
;; GridDataCell should be taking care of this one
;;
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
