(ns parsinghelpers)

(defn heading-level [s]
  (count (second (re-find #"^(\*+) " s))))

(defn heading-line? [s]
  (> (heading-level s) 0))
