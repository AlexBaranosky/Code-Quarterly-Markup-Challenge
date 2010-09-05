(ns markupparsing
  (:use node)
  (:use common)
  (:import (java.io BufferedReader FileReader)))

(defn parse [s]
  (make-node :body))

(defn parse-file [f]
  (let [file-contents (slurp f)]
    (parse file-contents)))
