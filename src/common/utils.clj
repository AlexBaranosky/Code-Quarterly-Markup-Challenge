(ns common.utils
  (:use [clojure.contrib.str-utils2 :only (split)]))

(defn resource [f]
  (str "C:\\Users\\Alex and Paula\\Documents\\Software Projects\\CodeChallengeMarkup\\resources\\" f))

(defn txt-resource [file-sans-extension]
  (resource (str file-sans-extension ".txt")))

(defn xml-resource [file-sans-extension]
  (resource (str file-sans-extension ".xml")))

(def zip
  (partial map list))

(defn re-count [rx s]
  (count (re-seq  rx s)))

(defn matches? [rx s]
  (> (re-count rx s) 0))