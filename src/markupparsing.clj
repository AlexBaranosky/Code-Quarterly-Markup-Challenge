(ns markupparsing
  (:use node)
  (:use common)
  (:use fnparse.fnparse)
  (:import (java.io BufferedReader FileReader)))

(defn parse [s]
  (if (= s "")
    (make-node :body)
    (make-node :body (make-node :p s))))
