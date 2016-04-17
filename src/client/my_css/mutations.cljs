(ns my-css.mutations
  (:require [untangled.client.mutations :as m]
            [untangled.dom :refer [unique-key]]))

(defmethod m/mutate 'nav/change-tab [{:keys [state]} k {:keys [target]}]
  {:action (fn [] (swap! state assoc :current-tab [target :singleton]))})

(defmethod m/post-mutate :default [{:keys [state]} _ _]
  ; this won't even print:
  (println "Nothing in default for post-mutate"))
