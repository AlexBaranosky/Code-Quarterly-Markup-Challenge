(ns xml-transformation
  (:use xml)
  (:use common.utils))

(defn to-xml [node]
  (if (string? node)
    node
    (let [name (name (:name node))
          children (:children node)
          children-string (apply str (map to-xml children))]
      (tag name children-string))))