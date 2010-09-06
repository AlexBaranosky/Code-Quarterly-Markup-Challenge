(ns node)

(defstruct node :name :children)

(def make-node
  (partial struct node))

(def p
  (partial make-node :p))

(def body
  (partial make-node :body))


;TODO: something to go through and take (keywd x y) and turn it into (make-node :keywd x y) for ANY keywd
;(defmacro body [forms]
;  forms)