(ns facts.commonfacts
  (:use common.utils)
  (:use midje.sweet))

(fact
  (zip [1 2 3] [4 5 6]) => [[1 4] [2 5] [3 6]])

