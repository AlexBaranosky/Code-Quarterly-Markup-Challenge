(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.string))

(defn parse-heading [s]
  (let [level (heading-level s)
        content (.substring s (inc level))]
    (h level content)))

(defn parse-headings [s]
  (let [sections (split-on-blank-lines s)]
    (map parse-heading sections)))

(defn parse-paragraphs [s]
  (let [sections (split-on-blank-lines s)]
    (map p sections)))

(defn parse-blockquote [s]
  (let [content (.substring s 2)]
    (blockquote (p content))))

(defn parse-other [s]
  (let [sections (split-on-blank-lines s)
        parserfn #(if (heading? %) (parse-heading %) (p %))]
    (map parserfn sections)))

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
