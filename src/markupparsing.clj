(ns markupparsing
  (:use node)
  (:use common)
  (:use file)
  (:import (java.io BufferedReader FileReader)))

(defn parse [s]
  (make-node :body))

(defn parse-file [f]
  (let [path (resource f)
        file-contents (read-file path)]
    (parse file-contents)))
