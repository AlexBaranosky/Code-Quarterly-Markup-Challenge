(ns node-tests
  (:use midje.sweet)
  (:use node))

(fact
  (make-node :div ...child1... ...child2...) => 
  (struct node :div [...child1... ...child2...]))

(fact
  (parse-file "01_empty.txt") => 
  (make-node :body))
