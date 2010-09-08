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

(defn only [seq]
  (if (= 1 (count seq))
    (first seq)
    (throw (Exception. "sequence must have exactly one element"))))

(defn any? [pred seq]
  (not-every? #(not (pred %)) seq))