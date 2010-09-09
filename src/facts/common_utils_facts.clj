(ns facts.commonfacts
  (:use common.utils)
  (:use midje.sweet))

(fact
  (zip [1 2 3] [4 5 6]) => [[1 4] [2 5] [3 6]])

(fact (any? odd? [1 3]) => true)
(fact (any? odd? [2 4]) => false)
(fact (any? odd? [1 4]) => true)

(fact (re-count #"1" "1") => 1)
(fact (re-count #"1" "11") => 2)
(fact (re-count #"1" "111") => 3)