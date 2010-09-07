(ns common.string
  (:use [clojure.contrib.str-utils2 :only (split)]))

(defn blank? [s]
  (nil? (re-find #"\S" s)))

(defn split-on-blank-lines [s]
  (split (.trim s) #"\s*\n\s*\n\s*"))