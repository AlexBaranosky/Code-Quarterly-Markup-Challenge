(ns node)

(defstruct node :name :children)

(defn make-node [name children]
  (if (or (seq? children) (vector? children) (nil? children))
    (struct node name children)
    (struct node name [children])))

(def body
  (partial make-node :body))

(def p
  (partial make-node :p))

(def h1
  (partial make-node :h1))

;TODO: something to go through and take (keywd x y) and turn it into (make-node :keywd x y) for ANY keywd
;(defmacro body [forms]
;  forms)