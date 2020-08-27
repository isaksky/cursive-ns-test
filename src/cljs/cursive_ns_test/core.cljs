(ns cursive-ns-test.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [cursive-ns-test.events :as events]
   [cursive-ns-test.views :as views]
   [cursive-ns-test.config :as config]
   [lambdaisland.glogi :as log]
   [lambdaisland.glogi.console :as glogi-console]
   ))


(glogi-console/install!)

(log/set-levels
  {:glogi/root   :info    ;; Set a root logger level, this will be inherited by all loggers
   'my.app.thing :trace   ;; Some namespaces you might want detailed logging
   'my.app.other :error   ;; or for others you only want to see errors.
   })

(log/info :hello {:message "Hello, world!"})

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
