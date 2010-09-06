(ns facts.markupparsingfacts
  (:use markupparsing)
  (:use node)
  (:use midje.sweet))

;"01_empty"
(fact
  (parse "") => (body nil))

;02_simple_paragraph
(fact (parse "This is a simple paragraph.") =>
       (body [(p "This is a simple paragraph.")]))

;04_two_paragraphs.txt
(fact (parse "This is paragraph number one.

This is paragraph number two.")
  =>
   (body [(p "This is paragraph number one.") (p "This is paragraph number two.")]))
