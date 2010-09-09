(ns token
  (:use parse-string-predicates)
  (:use node)
  (:use common.string))

(defn parse-heading [s]
  (let [hlevel (heading-level-of s)
        content (.substring s (inc hlevel))]
    (h hlevel content)))

(defn parse-headings [token]
  (map parse-heading (:sections token)))

(defn parse-paragraphs [token]
  (map p (map trim-n-crunch-whitespace (:sections token))))

(defn parse-blockquotes [token]
  [(blockquote (parse-paragraphs token))])

(defstruct token :sections :parsefn)

(def make-token
  (partial struct token))

(defn heading-token [text-blocks]
  (make-token text-blocks parse-headings))

(defn blockquote-token [text-blocks]
  (make-token text-blocks parse-blockquotes))

(defn paragraph-token [text-blocks]
  (make-token text-blocks parse-paragraphs))




