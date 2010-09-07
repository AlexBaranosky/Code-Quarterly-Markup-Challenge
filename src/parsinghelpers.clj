(ns parsinghelpers
  (:use common.string))

(defn multi-sectioned? [s]
  (< 1 (count (split-on-blank-lines s))))

(defn heading-level [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading-line? [s]
  (> (heading-level s) 0))


(defn blockquote? [s]
  ( > (count (re-find #"^  " s)) 0))
