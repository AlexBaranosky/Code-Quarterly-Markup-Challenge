(ns markupparsing
  (:use node)
  (:use common.string)
  (:import (java.io BufferedReader FileReader)))

(defn parse [s]
  (let [children
        (cond
          (blank? s)
          nil

          :else
          (let [lines (split-non-blank-chunks s)]
            (map p lines)))]
    (body children)))
