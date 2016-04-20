(ns my-css.components.grid
  (:require [om.next :as om :refer-macros [defui]]
            [my-css.components.general :as gen]
            [om.dom :as dom]))

(defui GridDataCell
       static om/Ident
       (ident [this props]
              [:gas-at-location/by-id (:grid-cell/id props)])
       static om/IQuery
       (query [this]
              [:grid-cell/id
               {:system-gas (om/get-query gen/SystemGas)}
               {:tube (om/get-query gen/Location)}]))
