(ns client-core
  (:require [om.next :as om]
            [goog.dom :as gdom]))

(declare map->Application)

;;
;; Before we move to UnTangled, lets just use the same structure. Will test it works here then move it to
;; om-alarming
;;
(defn new-untangled-client
  [& {:keys [initial-state my-parser]
      :or   {initial-state {} my-parser nil}}]
  (map->Application {:initial-state initial-state :my-parser my-parser}))

(defprotocol MyApplication
  (mount [this root-component target-dom-id] "Start the webapp on the given DOM ID or DOM Node."))

(defrecord Application [initial-state my-parser reconciler]
  MyApplication
  ;; dom-id-or-node:- "main-app-area" or "app"
  (mount [this root-component dom-id-or-node my-state]
    (let [node (if (string? dom-id-or-node)
                 (gdom/getElement dom-id-or-node)
                 dom-id-or-node)
          rec (om/reconciler {:normalize true
                              :state     my-state
                              :parser    my-parser
                              ;:logger    my-logger
                              })]
      (om/add-root! rec
                    root-component
                    node)
      (assoc this :reconciler rec))))

