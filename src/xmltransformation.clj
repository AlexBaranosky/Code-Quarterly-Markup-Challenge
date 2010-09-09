(ns xmltransformation
  (:use xml)
  (:use common.utils))

(defn to-xml [node]
  (if (string? node)
    node
    (let [name (name (:name node))
          children (:children node)]
      (tag name (apply str (map to-xml children))))))