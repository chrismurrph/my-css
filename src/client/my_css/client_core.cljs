(ns my-css.client-core
  (:require [om.next :as om]
            [goog.dom :as gdom]))

(declare map->Application)

;;
;; Before we move to UnTangled, lets just use the same structure. Will test it works here then move it to
;; om-alarming
;;
(defn new-not-untangled-client
  [& {:keys [initial-state my-parser]
      :or   {initial-state {} my-parser nil}}]
  (map->Application {:initial-state initial-state :my-parser my-parser}))

(defprotocol MyApplication
  (mount [this root-component target-dom-id] "Start the webapp on the given DOM ID or DOM Node."))

(defrecord Application [initial-state my-parser reconciler]
  MyApplication
  ;; dom-id-or-node:- "main-app-area" or "app"
  (mount [this root-component dom-id-or-node]
    (let [node (if (string? dom-id-or-node)
                 (gdom/getElement dom-id-or-node)
                 dom-id-or-node)
          rec (om/reconciler {:normalize false
                              :state     initial-state
                              :parser    my-parser
                              ;:logger    my-logger
                              })]
      (println "rec:" (keys rec))
      (println "parser:" (-> rec :config :parser))
      (println "root-component:" root-component)
      (println "node:" node)
      (println "starting current tab 1:" (:app/current-tab @initial-state))
      (println "starting current tab 2:" (:app/current-tab (om.next/app-state rec)))
      (om/add-root! rec
                    root-component
                    node)
      (assoc this :reconciler rec))))

