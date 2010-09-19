(ns facts.commonfacts
  (:use common.utils)
  (:use midje.sweet))

(fact
  (zip [1 2 3] [4 5 6]) => [[1 4] [2 5] [3 6]])

(fact (re-count #"1" "1") => 1)
(fact (re-count #"1" "11") => 2)
(fact (re-count #"1" "111") => 3)

(fact (take-until-second odd? [1 2 2 3 4 7]) => [1 2 2])
(fact (take-until-second odd? [3 4 7]) => [3 4])
(fact (take-until-second odd? [7]) => [7])
(fact (take-until-second odd? []) => [])

(fact (partition-until-second odd? [1 2 2 3 4 7 9]) => [[1 2 2] [3 4] [7] [9]])
(fact (partition-until-second odd? []) => [])