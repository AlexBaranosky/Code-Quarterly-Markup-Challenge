(ns markupparsing
  (:use node)
  (:use token)
  (:use parsinghelpers)
  (:use common.utils)
  (:use common.string))

(defn parse-heading [s]
  (let [hlevel (heading-level s)
        content (.substring s (inc hlevel))]
    (h hlevel content)))

(defn parse-headings [token]
  (map parse-heading (:sections token)))

(defn parse-blockquotes [token]
  [(blockquote (map p (map trim-n-crunch-whitespace (:sections token))))])

(defn parse-paragraphs [token]
  (map p (map trim-n-crunch-whitespace (:sections token))))

(defstruct token :sections :parsefn)

(def make-token
  (partial struct token))

(defn heading-token [text-blocks]
  (make-token text-blocks parse-headings))

(defn blockquote-token [text-blocks]
  (make-token text-blocks parse-blockquotes))

(defn paragraph-token [text-blocks]
  (make-token text-blocks parse-paragraphs))

(defn not-heading?-not-blockquote? [text-blocks]
  (not (or (heading? text-blocks) (blockquote? text-blocks))))

(defn empty?-or-blank? [text-blocks]
  (or (= text-blocks [[]]) (empty? text-blocks) (blank-sections? text-blocks)))

(def empty-token (make-token [] (fn [token] [])))
(def empty-token-seq [empty-token])

(defn take-first-token [text-blocks]
  (cond
    (empty?-or-blank? text-blocks)    empty-token
    (heading? (first text-blocks))    (heading-token    (take-while heading? text-blocks))
    (blockquote? (first text-blocks)) (blockquote-token (take-while blockquote? text-blocks))
    :else                             (paragraph-token  (take-while not-heading?-not-blockquote? text-blocks))))

(defn remaining-text-blocks [text-blocks]
  (if (empty?-or-blank? text-blocks) []
    (drop (count (:sections (take-first-token text-blocks))) text-blocks)))

(defn tokenize [text-blocks]
  (if (empty?-or-blank? text-blocks) []
    (cons (take-first-token text-blocks) (tokenize (remaining-text-blocks text-blocks)))))

(defn process-tokens [tokens]
  (reduce concat [] (map #((:parsefn %) %) tokens))
;  (map #((:parsefn %) %) tokens)
  )

(defn parse [s]
  (let [text-blocks (split-on-blank-lines s)
        tokens (tokenize text-blocks)
        children (process-tokens tokens)]
    (println tokens)
    (println "***")
    (println children)
    (println)
    (body children)))