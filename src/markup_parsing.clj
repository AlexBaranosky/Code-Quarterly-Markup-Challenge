(ns markup-parsing
  (:use node)
  (:use token)
  (:use parse-string-predicates)
  (:use common.string))

(defn take-first-token [text-blocks]
  (let [block (first text-blocks)]
    (cond
      (heading? block)      (heading-token (take-while heading? text-blocks))
      (blockquote? block)   (blockquote-token (take-while blockquote? text-blocks))
      (ordered-list? block) (ordered-list-token (take-while ordered-list? text-blocks))
      (unordered-list? block) (unordered-list-token (take-while unordered-list? text-blocks))
      (verbatim? block)     (verbatim-token (take-while verbatim? text-blocks))
      :else                 (paragraph-token (take-while paragraph? text-blocks)))))

(defn remaining-text-blocks [text-blocks]
  (let [length-of-first (count (:sections (take-first-token text-blocks)))]
    (drop length-of-first text-blocks)))

(defn tokenize [text-blocks]
  (if (empty? text-blocks) []
    (cons (take-first-token text-blocks) (tokenize (remaining-text-blocks text-blocks)))))

(defn parse-tokens [tokens]
  (apply concat (map #((:parsefn %) %) tokens)))

(defn parse [s]
  (let [text-blocks (split-on-blank-lines s)
        tokens (tokenize text-blocks)
        children (parse-tokens tokens)]
    (body children)))