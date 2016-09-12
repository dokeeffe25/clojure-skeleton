(ns leiningen.new.clojure-skeleton
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main])
  (:use [leiningen.new.templates :only [sanitize-ns]]))

(def render (renderer "clojure-skeleton"))

(defn clojure-skeleton
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' clojure-skeleton project.")
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "src/core.clj" data)]
             ["src/{{sanitized}}/components/config.clj"
              (render "src/components/config.clj" data)]
             ["resources/config.edn"
              (render "resources/config.edn" data)])))
