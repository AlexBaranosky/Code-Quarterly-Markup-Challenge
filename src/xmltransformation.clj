(ns xmltransformation
  (:use xml))

(defn to-xml
  ([node]
    (println node)
    (cond
      (string? node)
      node
      :else
      (let [name (name (:name node))
            children (:children node)
            tagfn (partial tag name)]
        (if (= nil children)
          (tagfn nil)
          (tagfn (to-xml children))))))) 