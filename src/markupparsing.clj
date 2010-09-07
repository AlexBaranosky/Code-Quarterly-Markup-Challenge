(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.string))

(defn parse-heading [s]
  (let [heading-level (num-leading-asterisks s)
        content (.substring s (inc heading-level))]
    (h heading-level content)))

(defn parse-headings [s]
  (let [lines (split-non-blank-chunks s)]
    (map parse-heading lines)))

(defn parse-paragraphs [s]
  (let [lines (split-non-blank-chunks s)]
    (map p lines)))

(defn parse [s]
  (let [children
        (cond
          (blank? s)
          nil

          (> (num-leading-asterisks s) 0)
          (parse-headings s)

          :else
          (parse-paragraphs s))]
    (body children)))
