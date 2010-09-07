(ns facts.parsing-helpers
  (:use midje.sweet))

(fact (num-leading-asterisks "") => 0)
(fact (num-leading-asterisks "*") => 0)
(fact (num-leading-asterisks "* ") => 1)
(fact (num-leading-asterisks "** ") => 2)
(fact (num-leading-asterisks "*** ") => 3)
(fact (num-leading-asterisks "*** *** ") => 3)
(fact (num-leading-asterisks "***      *") => 3)
(fact (num-leading-asterisks "* This is a top level header") => 1)
