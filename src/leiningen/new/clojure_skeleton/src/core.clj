(ns {{ns-name}}.core
  (:require [clojure.core.async :as async]
            [com.stuartsierra.component :as component]
            [taoensso.timbre :as timbre]
            [{{ns-name}}.components.config :as config]))

(timbre/refer-timbre)

(def system nil)

(defn create-system [config-files]
  (alter-var-root
   #'system
   (constantly (component/system-map
                :config (config/create-config-component
                         config-files)))))

(defn stop []
  (info "Stopping System")
  (alter-var-root #'system component/stop))

(defn -main [& configs]
  (create-system (into [] configs))
  (alter-var-root #'system component/start))
