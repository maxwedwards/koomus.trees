(ns koomus.trees.metrics-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [com.stuartsierra.component :as component]
            [metrics.core :refer [new-registry]]
            [koomus.trees.metrics :as metrics]
            [clojure.reflect :refer [reflect]]
            [clojure.pprint :refer [pprint]]
            [metrics.reporters.graphite :as graphite])
  (:import [java.util.concurrent TimeUnit]
           [com.codahale.metrics MetricFilter]))

(def generate-reporter #'metrics/generate-reporter)

(facts "Metrics tests"
  
  (fact "generate-reporter should return a new graphite reporter" 
    (let [host "127.0.0.1" reg (new-registry)]
      (generate-reporter reg host) => irrelevant
      (provided 
        (graphite/reporter 
          reg {:host host
               :prefix "koomus-metrics"
               :rate-unit TimeUnit/SECONDS
               :duration-unit TimeUnit/MILLISECONDS
               :filter MetricFilter/ALL})  => irrelevant)))
  
)

