(ns markupparsing
  (:use node)
  (:use common)
  (:use fnparse.fnparse)
  (:import (java.io BufferedReader FileReader)))

;(defn tokenize-multi-line [s]
;  (filter #(not (= "" %)) (.split s #"\s*\n\s*\n\s*")))
;
;(defn multi-line? [s]
;  (nil? (rest (tokenize-multi-line s))))

(defn parse [s]
;  (println (str "'" s "'"))
  (let [children
        (if (re-matches #"\s+" s)
          nil
;          (if (multi-line? s)
;            (let [lines (tokenize-multi-line s)]
;              (map #(make-node :p %) lines))
            (make-node :p s))]
    (make-node :body children)))
