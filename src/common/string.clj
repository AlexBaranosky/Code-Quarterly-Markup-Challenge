(ns common.string
  (:use clojure.contrib.str-utils)
  (:use [clojure.contrib.str-utils2 :only (split)]))

(defn blank? [s]
  (nil? (re-find #"\S" s)))

(defn split-on-blank-lines [s]
  (if (blank? s)
    []
    (split s #"\s*\n\s*\n")))

(defn trim-n-crunch-whitespace [s]
  (let [trimmed (.trim s)]
    (re-gsub #"\s*\n\s*" " " trimmed)))

(defn trim-right [s]
  (re-gsub #"\s+$" "" s))

(defn trim-3-rx [s]
  (let [first-pass (re-gsub #"(^   )" "" s)]
    (re-gsub #"(\n   )" "\n" first-pass)))