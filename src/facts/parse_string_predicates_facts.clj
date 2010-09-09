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

(fact (blockquote? "  blockquote!") => true)
(fact (blockquote? "not") => false)
(fact (blockquote? " not") => false)
(fact (blockquote? "   not") => false)
(fact (blockquote? "    not") => false)

(fact (paragraph? "* heading") => false)
(fact (paragraph? "  blockquote!") => false)
(fact (paragraph? "paragraph") => true)

(fact (verbatim? "   verbatim") => true)
(fact (verbatim? "not") => false)
(fact (verbatim? " not") => false)
(fact (verbatim? "  not") => false)
(fact (verbatim? "    not") => false)