(ns xmltransformation
  (:use xml)
  (:use common.utils))

(defn to-xml [node]
  (println (str "node: " node))
  (println (str "children: " (:children node)))
  (cond
    (string? node)
    node

    :else
    (let [name (name (:name node))
          children (:children node)
          tagfn (partial tag name)]
      (if (nil? children)
        (tagfn nil)
        (tagfn (apply str (map to-xml children)))))))