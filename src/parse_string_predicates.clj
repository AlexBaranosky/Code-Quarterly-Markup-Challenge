(ns parse-string-predicates
  (:use common.string)
  (:use common.utils))

(defn heading-level-of [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading? [s]
  (> (heading-level-of s) 0))

(defn blockquote? [s]
  (matches? #"^  [^#\s]" s))

(defn verbatim? [s]
  (matches? #"^   " s))

(defn ordered-list? [s]
  (matches? #"^  #" s))

(defn paragraph? [s]
  (not (or (heading? s) (blockquote? s) (verbatim? s))))