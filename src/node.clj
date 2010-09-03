(ns node)

(defstruct node :node-type :children)

(defn make-node [node-type & children]
  (struct node node-type children))
