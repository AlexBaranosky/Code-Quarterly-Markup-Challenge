(ns facts.xmlfacts
  (:use xml)
  (:use midje.sweet))

(fact
  (start-tag "div") => "<div>")

(fact
  (end-tag "div") => "</div>")

(fact
  (self-closing-tag "br") => "<br/>")
