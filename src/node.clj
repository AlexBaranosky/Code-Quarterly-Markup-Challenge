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

(defn h [n children]
  (let [keywd (keyword (str "h" (Integer/toString n)))]
    (make-node keywd children)))

(def h1
  (partial h 1))

(def h2
  (partial h 2))

(def h3
  (partial h 3))

;TODO: something to go through and take (keywd x y) and turn it into (make-node :keywd x y) for ANY keywd
;(defmacro body [forms]
;  forms)