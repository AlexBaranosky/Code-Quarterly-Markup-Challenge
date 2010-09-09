(ns facts.commonfacts
  (:use common.utils)
  (:use midje.sweet))

(fact
  (zip [1 2 3] [4 5 6]) => [[1 4] [2 5] [3 6]])

(fact (re-count #"1" "1") => 1)
(fact (re-count #"1" "11") => 2)
(fact (re-count #"1" "111") => 3)