(ns facts.nodefacts
  (:use node)
  (:use midje.sweet))

(fact
  (make-node :body [...child1... ...child2...]) =>
  (struct node :body [...child1... ...child2...]))

(fact
  (p ...children...) => (make-node :p ...children...))

(fact
  (body ...children...) => (make-node :body ...children...))
