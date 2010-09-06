(ns xmltransformation
  (:use xml))

(defn to-xml
  ([node]
;    (println node)
    (if (string? node)
      node
      (let [name (name (:name node))
            children (:children node)
            content (if (= nil children) "" (to-xml children))]
        (tag name content)))))
