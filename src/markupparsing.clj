(ns markupparsing
  (:use node)
  (:use common)
  (:import (java.io BufferedReader FileReader)))

(defn parse [s]
  (println (str "'" s "'"))
  (let [children
        (cond
          (blank? s)
          nil

          (multi-line? s)
          (let [lines (split-non-blank-chunks s)]
            (println (str "lines: " lines))
            (map #(make-node :p %) lines))

          :else
          (list (make-node :p s)))]
    (make-node :body children)))
