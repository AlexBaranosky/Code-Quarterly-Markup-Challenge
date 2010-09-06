(ns facts.nodefacts
  (:use node)
  (:use midje.sweet))

(fact
  (make-node :body [...child1... ...child2...]) =>
  (struct node :body [...child1... ...child2...]))

(fact
  (make-node :body ...child...) =>
  (struct node :body [...child...]))

(fact
  (make-node :body nil) => (struct node :body nil))

(fact
  (body ...children...) => (make-node :body ...children...))

(fact
  (p ...children...) => (make-node :p ...children...))

(fact
  (h1 ...children...) => (make-node :h1 ...children...))
