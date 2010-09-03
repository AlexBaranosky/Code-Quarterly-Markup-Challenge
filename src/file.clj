(ns file
  (:import (java.io BufferedReader FileReader)))

(defn read-file [f]
  (with-open [reader (BufferedReader. (FileReader. f))]
    (line-seq reader)))
