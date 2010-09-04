(ns node)

(defstruct node :node-type :children)

(def make-node
  (partial struct node))