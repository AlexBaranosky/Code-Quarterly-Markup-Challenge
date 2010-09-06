(ns node)

(defstruct node :name :children)

(def make-node
  (partial struct node))