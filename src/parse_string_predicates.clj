(ns parse-string-predicates
  (:use common.string)
  (:use common.utils))

(defn multi-sectioned? [s]
  (< 1 (count (split-on-blank-lines s))))

(defn heading-level-of [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading? [s]
  (> (heading-level-of s) 0))

(defn blockquote? [s]
  (> (count (re-find #"^  " s)) 0))

(defn not-heading?-not-blockquote? [s]
  (not (or (heading? s) (blockquote? s))))