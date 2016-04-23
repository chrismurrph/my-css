(ns my-css.mutations
  (:require [untangled.client.mutations :as m]
            [untangled.dom :refer [unique-key]]))

(comment
  (defmethod m/mutate 'nav/change-tab [{:keys [state]} k {:keys [target]}]
    {:action (fn [] (swap! state assoc :app/current-tab [target :singleton]))}))

(defn unload [state prev-target]
  (case prev-target
    :app/trending (-> state
                      (update-in [:app/trending :singleton] dissoc
                                 :grid/gas-query-grid
                                 :graph/trending-graph
                                 ))
    state))

;;
;; Only happens once so we could check. However we should probably do this as a post-load thing. Or another idea is
;; to move current out and new in. Thus will achieve not rendering tabs that are not visible
;;
(defn change-tab [old-st new-target]
  (let [prev-target (first (get old-st :app/current-tab))
        new-state (-> old-st
                      (unload prev-target)
                      (assoc :app/current-tab [new-target :singleton]))]
    (case new-target
      :app/trending (-> new-state
                        (update-in [:app/trending :singleton] assoc
                                   :grid/gas-query-grid [:gas-query-grid/by-id 10800]
                                   :graph/trending-graph [:trending-graph/by-id 10300]
                                   ))
      new-state)))

(defmethod m/mutate 'nav/load-tab [{:keys [state]} k {:keys [target]}]
  {:action (fn [] (swap! state change-tab target))})

(defmethod m/post-mutate :default [{:keys [state]} _ _]
  ; this won't even print:
  (println "Nothing in default for post-mutate"))
