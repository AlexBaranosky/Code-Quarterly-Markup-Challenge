(ns parsinghelpers)

(defn num-leading-asterisks [s]
  (count (second (re-find #"^(\*+) " s))))
