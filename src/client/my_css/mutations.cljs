(ns my-css.mutations
  (:require [untangled.client.mutations :as m]
            [untangled.dom :refer [unique-key]]))

(defmethod m/mutate 'nav/change-tab [{:keys [state]} k {:keys [target]}]
  {:action (fn [] (swap! state assoc :app/current-tab [target :singleton]))})

;;
;; Only happens once so we could check. However we should probably do this as a post-load thing
;;
(defmethod m/mutate 'nav/lazy-load-tab [{:keys [state]} k {:keys [target]}]
  {:action (fn [] (swap! state update-in [:app/trending :singleton] assoc :grid/gas-query-grid [:gas-query-grid/by-id 10800]))})

(defmethod m/post-mutate :default [{:keys [state]} _ _]
  ; this won't even print:
  (println "Nothing in default for post-mutate"))
