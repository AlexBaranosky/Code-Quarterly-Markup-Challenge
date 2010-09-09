(ns facts.commonstringfacts
  (:use common.string)
  (:use midje.sweet))

(fact (blank? "") => true)

(fact (blank? "                           ") => true)

(fact (blank? "

  ") => true)

(fact (blank? " goat ") => false)

(fact (blank? "
  go   at

     ") => false)

(fact (split-on-blank-lines "   groom-self  abc
  def

  ghi

  paula balla") => ["   groom-self  abc
  def" "  ghi" "  paula balla"])

(fact (split-on-blank-lines "123

  abc") => ["123" "  abc"])

(fact (split-on-blank-lines "") => [])

(fact (trim-n-crunch-whitespace "  ABC") => "ABC")
(fact (trim-n-crunch-whitespace "  ABC
  EFG ") => "ABC EFG")
