(ns xml)

(defn start-tag [node-name]
  (str "<" node-name ">"))

(defn end-tag [node-name]
  (str "</" node-name ">"))

(defn self-closing-tag [node-name]
  (str "<" node-name "/>"))

(defn tag [name content]
  (if (= nil content)
    (self-closing-tag name)
    (str (start-tag name) content (end-tag name))))
