(ns parsinghelpers
  (:use common.string)
  (:use common.utils)
  (:use clojure.contrib.str-utils))

(defn multi-sectioned? [s]
  (< 1 (count (split-on-blank-lines s))))

(defn heading-level [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading? [s]
  (> (heading-level s) 0))

(defn heading-sections? [sections]
  (any? heading? sections))

(defn blockquote? [s]
  (> (count (re-find #"^  " s)) 0))

(defn blockquote-sections? [sections]
  (any? blockquote? sections))

(defn trim-n-crunch-whitespace [s]
  (let [trimmed (.trim s)]
    (re-gsub #"\s*\n\s*" " " trimmed)))

(defn blank-sections? [sections]
  (any? blank? sections))
