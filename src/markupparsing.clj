(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.utils)
  (:use common.string))

(defn parse-heading [s]
  (let [hlevel (heading-level s)
        content (.substring s (inc hlevel))]
    (h hlevel content)))

(defn parse-heading-sections [sections]
  (map parse-heading sections))

(defn parse-blockquote-sections [sections]
  (blockquote (map p (map trim-n-crunch-whitespace sections))))

(defn not-heading?-not-blockquote? [sections]
  (not (or (heading? sections) (blockquote? sections))))

(defn empty?-or-blank? [sections]
  (or (= sections [[]]) (empty? sections) (blank-sections? sections)))

(defn parse-p-sections [sections]
  (map p sections))

(def empty-sections-w-parserfn [[], (fn [x] [])])
(def empty-sections-w-parserfn-seq [empty-sections-w-parserfn])

(defn first-sections-w-parserfns [sections]
  (cond
    (empty?-or-blank? sections)
    empty-sections-w-parserfn

        (heading? (first sections))
        [(take-while heading? sections), parse-heading-sections]

        (blockquote? (first sections))
        [(take-while blockquote? sections), parse-blockquote-sections]

    :else ;p
;    [sections, parse-p-sections]))
    [(take-while not-heading?-not-blockquote? sections), parse-p-sections]))

(defn remaining-sections [sections]
  (if (empty?-or-blank? sections) []
    (drop (count (first (first-sections-w-parserfns sections))) sections)))

(defn parse-sections [sections-w-parserfn]
  (let [parserfn (second sections-w-parserfn)
        sections (first sections-w-parserfn)]
    (parserfn sections)))

(defn tokenize-into-sections-w-parserfns [sections]
  (if (empty?-or-blank? sections) empty-sections-w-parserfn-seq
    (cons (first-sections-w-parserfns sections) (tokenize-into-sections-w-parserfns (remaining-sections sections)))))

(defn parse [s]
  (let [text-blocks (split-on-blank-lines s)
        sections-w-parserfns (tokenize-into-sections-w-parserfns text-blocks)
        children (map parse-sections sections-w-parserfns)]
    (body children)))

