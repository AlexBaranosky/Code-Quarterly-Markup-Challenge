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

(fact (heading? "* Goat") => true)
(fact (heading? "Goat") => false)

(fact (multi-sectioned? "") => false)

(fact (multi-sectioned? " 123 abc") => false)

(fact (multi-sectioned? " 123
  abc") => false)

(fact (multi-sectioned? "123
") => false)

(fact (multi-sectioned? "123

  abc") => true)

(fact (blockquote? "  blockquote!") => true)
(fact (blockquote? "not") => false)

(fact (trim-n-crunch-whitespace "  ABC") => "ABC")
(fact (trim-n-crunch-whitespace "  ABC
  EFG") => "ABC EFG")
