(ns common.utils
  (:use [clojure.contrib.str-utils2 :only (split)])
  (:import (java.io File)))

(defn resource [f]
  (let [resource-path (.getCanonicalPath (File. "..\\..\\resources"))]
    (str resource-path "\\" f)))

(defn txt-resource [file-sans-extension]
  (resource (str file-sans-extension ".txt")))

(defn xml-resource [file-sans-extension]
  (resource (str file-sans-extension ".xml")))

(def zip
  (partial map list))

(defn re-count [rx s]
  (count (re-seq rx s)))

(defn matches? [rx s]
  (> (re-count rx s) 0))