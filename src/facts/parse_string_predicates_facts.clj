(ns facts.parse-string-predicates
  (:use parse-string-predicates)
  (:use midje.sweet))

(fact (heading-level-of "") => 0)
(fact (heading-level-of "*") => 0)
(fact (heading-level-of "* ") => 1)
(fact (heading-level-of "** ") => 2)
(fact (heading-level-of "*** ") => 3)
(fact (heading-level-of "*** *** ") => 3)
(fact (heading-level-of "***      *") => 3)
(fact (heading-level-of "* This is a top level header") => 1)

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

(fact (not-heading?-not-blockquote? "* heading") => false)
(fact (not-heading?-not-blockquote? "  blockquote!") => false)
(fact (not-heading?-not-blockquote? "paragraph") => true)