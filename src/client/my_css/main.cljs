(ns my-css.main
  (:require [my-css.root :as root]
            [my-css.core :as core]
            my-css.mutations
            [untangled.client.core :as uc]
            [om.dom :as dom]
            [om.next :as om]))

(reset! core/app (uc/mount @core/app root/App "app"))
