(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.string))

(defn parse-heading [s]
  (let [level (heading-level s)
        content (.substring s (inc level))]
    (h level content)))

(defn parse-blockquote [s]
  (let [paragraphs (map trim-n-crunch-whitespace (split-on-blank-lines s))]
    (blockquote (map p paragraphs))))

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
