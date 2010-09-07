(ns xmltransformation
  (:use xml)
  (:use common.utils))

(defn to-xml [node]
  (println (str "node: " node))
  (println (str "children: " (:children node)))
  (if (string? node)
    node
    (let [name (name (:name node))
          children (:children node)]
      (if (nil? children)
        (tag name nil)
        (tag name (apply str (map to-xml children)))))))