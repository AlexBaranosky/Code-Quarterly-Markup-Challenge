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

(defn take-until-second [pred coll]
  (cond
    (empty? coll) []

    (pred (first coll))
    (cons (first coll) (take-while #(not (pred %)) (rest coll)))

    :else
    (cons (first coll) (take-until-second pred (rest coll)))))

(defn partition-until-second [pred coll]
  (let [matches (take-until-second pred coll)]
    (if (empty? matches) []
      (cons matches (partition-until-second pred (drop (count matches) coll))))))