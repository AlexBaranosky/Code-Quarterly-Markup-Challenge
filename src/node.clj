(ns node)

(defstruct node :name :children)

(def make-node
  (partial struct node))

(def p
  (partial make-node :p))

(def body
  (partial make-node :body))