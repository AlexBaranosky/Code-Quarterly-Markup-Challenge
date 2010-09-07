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

  paula balla") => ["groom-self  abc
  def" "ghi" "paula balla"])

(fact (split-on-blank-lines "123

  abc") => ["123" "abc"])

(fact (multi-line? "") => false)

(fact (multi-line? " 123 abc") => false)

(fact (multi-line? " 123
  abc") => false)

(fact (multi-line? "123
") => false)

(fact (multi-line? "123

  abc") => true)
