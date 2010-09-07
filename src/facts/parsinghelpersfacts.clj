(ns facts.parsinghelpers
  (:use parsinghelpers)
  (:use midje.sweet))

(fact (heading-level "") => 0)
(fact (heading-level "*") => 0)
(fact (heading-level "* ") => 1)
(fact (heading-level "** ") => 2)
(fact (heading-level "*** ") => 3)
(fact (heading-level "*** *** ") => 3)
(fact (heading-level "***      *") => 3)
(fact (heading-level "* This is a top level header") => 1)

(fact (heading-line? "* Goat") => true)
(fact (heading-line? "Goat") => false)
