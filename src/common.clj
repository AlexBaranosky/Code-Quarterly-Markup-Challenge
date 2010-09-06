(ns common
  (:use [clojure.contrib.str-utils2 :only (split)]))

(defn resource [f]
  (str "C:\\Users\\Alex and Paula\\Documents\\Software Projects\\CodeChallengeMarkup\\resources\\" f))

(defn txt-resource [file-sans-extension]
  (resource (str file-sans-extension ".txt")))

(defn xml-resource [file-sans-extension]
  (resource (str file-sans-extension ".xml")))

(def zip
  (partial map list))

;string stuff

(defn blank? [s]
  (nil? (re-find #"\S" s)))

(defn split-non-blank-chunks [s]
  (split (.trim s) #"\n\s*\n\s*"))

(defn multi-line? [s]
  (< 1 (count (split-non-blank-chunks s))))