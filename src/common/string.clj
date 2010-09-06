(ns common.string
  (:use [clojure.contrib.str-utils2 :only (split)]))

(defn blank? [s]
  (nil? (re-find #"\S" s)))

(defn split-non-blank-chunks [s]
  (split (.trim s) #"\s*\n\s*\n\s*"))

(defn multi-line? [s]
  (< 1 (count (split-non-blank-chunks s))))