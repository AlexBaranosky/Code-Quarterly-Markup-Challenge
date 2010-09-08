(ns markupparsing
  (:use node)
  (:use parsinghelpers)
  (:use common.utils)
  (:use common.string))

(def section-determiners [blank-sections? heading-sections? blockquote-sections? #(not (or (heading-sections? %) (blockquote-sections? %)))])

(defn parse-p-sections [sections]
  (map p sections))

(defn parse-heading [s]
  (let [hlevel (heading-level s)
        content (.substring s (inc hlevel))]
    (h hlevel content)))

(defn parse-heading-sections [sections]
  (map parse-heading sections))

(defn parse-blockquote-sections [sections]
  (blockquote (map p (map trim-n-crunch-whitespace sections))))

(defn empty?-or-blank? [sections]
  (or (= sections [[]]) (empty? sections) (blank-sections? sections)))

(defn not-heading?-not-blockquote? [sections]
  (not (or (heading? sections) (blockquote? sections))))

(defn first-sections-w-parserfns [sections]
  (cond
    (empty?-or-blank? sections)
    []

    (heading? (first sections))
    [(take-while heading? sections), parse-heading-sections]

    (blockquote? (first sections))
    [(take-while blockquote? sections), parse-blockquote-sections]

    :else ;p
    [(take-while not-heading?-not-blockquote? sections), parse-p-sections]))

(defn remaining-sections [sections]
  (if (empty?-or-blank? sections)
    []
    (drop (count (first (first-sections-w-parserfns sections))) sections)))

(defn parse-section [section-w-parserfn]
  (println section-w-parserfn)
  (println ((second section-w-parserfn) (first section-w-parserfn)))
  ((second section-w-parserfn) (first section-w-parserfn)))

(defn tokenize-into-sections-w-parserfns [sections]
  (if (empty?-or-blank? sections)
    []
    (concat [(first-sections-w-parserfns sections)] (tokenize-into-sections-w-parserfns (remaining-sections sections)))))

(defn parse [s]
  (let [chunks (split-on-blank-lines s) 
        sections-w-parserfns (tokenize-into-sections-w-parserfns chunks)
        children (map parse-section sections-w-parserfns)]
    (println "sections-w-parserfns: ")
    (println sections-w-parserfns)
    (println "children: ")
    (println children)
    (body children)))