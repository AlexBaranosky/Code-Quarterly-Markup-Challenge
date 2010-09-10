(ns token
  (:use parse-string-predicates)
  (:use node)
  (:use common.string)
  (:use clojure.contrib.str-utils))

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

(defn parse-ordered-lists [token]
  [(ol (map #(li %) (map p (map trim-n-crunch-whitespace (map #(.substring % 3) (:sections token))))))])

;TODO refactor me
(defn parse-verbatims [token]
  [(pre (trim-right (apply str (interpose "\n\n" (map trim-3-rx (:sections token))))))])

(defstruct token :sections :parsefn)

(def make-token
  (partial struct token))

(defn heading-token [text-blocks]
  (make-token text-blocks parse-headings))

(defn blockquote-token [text-blocks]
  (make-token text-blocks parse-blockquotes))

(defn verbatim-token [text-blocks]
  (make-token text-blocks parse-verbatims))

(defn ordered-list-token [text-blocks]
  (make-token text-blocks parse-ordered-lists))

(defn paragraph-token [text-blocks]
  (make-token text-blocks parse-paragraphs))




