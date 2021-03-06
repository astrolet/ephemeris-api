(defproject ephemeris-api "0.0.1-SNAPSHOT"
  :description "Ephemeris HTTP API"
  :min-lein-version  "2.4.0" ;; due to lein-immutant
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ephemeris "0.0.1"]
                 [prismatic/schema "1.1.12"]
                 [pedestal-api "0.3.4" :exclusions [prismatic/schema]]
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"] ;; for dev
                 [io.pedestal/pedestal.immutant "0.5.7"] ;; for prod
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]
                 [org.slf4j/log4j-over-slf4j "1.7.30"]
                 [org.clojure/tools.logging "1.0.0"]
                 [jarohen/nomad "0.9.1"]
                 [environ "1.1.0"]
                 [stencil "0.5.0"]]
  :plugins [[lein-environ "1.1.0"]]
  :env {:ever :project/version ;; ephemeris-api v[ersion]
        :base "/api/"} ;; keep the trailing /
  :source-paths ["src"]
  :resource-paths ["resources"]
  :global-vars {*warn-on-reflection* false}
  :profiles
    {:dev {:source-paths ["dev" "src"]
           :jvm-opts ["-Dnomad.env=dev" "--illegal-access=debug"]
           :dependencies [[ns-tracker "0.4.0"]
                          [proto-repl "0.3.1"]
                          [martian-test "0.1.11"]
                          [midje "1.9.9"]
                          [midje-notifier "0.3.0"]]
           :plugins [[lein-midje "3.2.2"]
                     [lein-ancient "0.6.15"]
                     [lein-immutant "2.1.0"]]
           :sass {:source "resources/sass" :target "resources/public/css"}
           :immutant {:nrepl-port 0
                      :nrepl-interface :management}
           :repl-options {:timeout 300000 ;; 5 minutes
                          :init-ns dev}}
     :repl
       {:ultra {:repl {:sort-keys false
                       :map-coll-separator :line}}}
     :uberjar {:main ephemeris-api.server :aot :all}}
  :immutant {:init pedestal-immutant.server/initialize
             :resolve-dependencies true
             :context-path "/"
             :war {:name "ephemeris-api"}}
  :aliases {"test" ["midje"]
            "autotest" ["midje" ":autotest"]
            "uberwar" ["immutant" "war"]}
  :main ^:skip-aot ephemeris-api.server
  :target-path "target/"
  :pom-location "target/"
  :uberjar-name "server.jar"
  :deploy-branches ["master"])
