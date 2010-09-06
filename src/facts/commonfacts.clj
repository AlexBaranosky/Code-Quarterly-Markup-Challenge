(ns facts.commonfacts
  (:use common)
  (:use midje.sweet))

(fact (blank? "") => true)

(fact (blank? "                           ") => true)

(fact (blank? "

  ") => true)

(fact (blank? " goat ") => false)

(fact (blank? "
  go   at

     ") => false)

(fact (split-non-blank-chunks "   groom-self  abc
  def

  ghi

  paula balla") => ["groom-self  abc
  def" "ghi" "paula balla"])

(fact (split-non-blank-chunks "123

  abc") => ["123" "abc"])

(fact (multi-line? "") => false)

(fact (multi-line? " 123 abc") => false)

(fact (multi-line? " 123
  abc") => false)

(fact (multi-line? "123
") => false)

(fact (multi-line? "123

  abc") => true)

