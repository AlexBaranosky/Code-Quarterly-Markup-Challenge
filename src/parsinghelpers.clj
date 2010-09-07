(ns parsinghelpers
  (:use common.string)
  (:use clojure.contrib.str-utils))

(defn multi-sectioned? [s]
  (< 1 (count (split-on-blank-lines s))))

(defn heading-level [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading? [s]
  (> (heading-level s) 0))

(defn blockquote? [s]
  ( > (count (re-find #"^  " s)) 0))

(defn trim-n-crunch-whitespace [s]
  (let [trimmed (.trim s)]
    (re-gsub #"\s*\n\s*" " " trimmed)))
