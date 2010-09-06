(ns xmltransformation)

(defn start-tag [node-name]
  (str "<" node-name ">"))

(defn end-tag [node-name]
  (str "</" node-name ">"))

(defn to-xml [node]
  (let [name (:name node)
        children (:children node)
        content (if (= nil children) "" (to-xml children))]
    (str
      (start-tag name)
      content
      (end-tag name))))
