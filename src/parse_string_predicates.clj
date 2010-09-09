(ns parse-string-predicates
  (:use common.string)
  (:use common.utils))

(defn heading-level-of [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading? [s]
  (> (heading-level-of s) 0))

(defn blockquote? [s]
  (= (re-count #"^\s{2}\S" s) 1))

(defn verbatim? [s]
  (= (re-count #"^\s{3}" s) 1))

(defn paragraph? [s]
  (not (or (heading? s) (blockquote? s) (verbatim? s))))