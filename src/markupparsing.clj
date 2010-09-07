(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.string))

(defn parse-heading [s]
  (let [level (heading-level s)
        content (.substring s (inc level))]
    (h level content)))

(defn parse-headings [s]
  (let [lines (split-on-blank-lines s)]
    (map parse-heading lines)))

(defn parse-paragraphs [s]
  (let [lines (split-on-blank-lines s)]
    (map p lines)))

(defn parse-blockquote [s]
  (let [content (.substring s 2)]
    (blockquote (p content))))

(defn parse-other [s]
  (let [lines (split-on-blank-lines s)
        parserfn #(if (heading-line? %) (parse-heading %) (p %))]
    (map parserfn lines)))

(defn parse [s]
  (let [children
        (cond
          (blank? s)
          nil

          (blockquote? s)
          (parse-blockquote s)

          :else
          (parse-other s))]
    (body children)))
