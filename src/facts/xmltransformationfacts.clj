(ns facts.xmltransformationfacts
  (:use xmltransformation)
  (:use node)
  (:use midje.sweet))

(fact
  (to-xml (body nil))
  =>
  "<body/>")

(fact
  (to-xml (body (p "This is a simple paragraph.")))
  =>
  "<body><p>This is a simple paragraph.</p></body>")

(fact
  (to-xml (body [(p "This is paragraph number one.") (p "This is paragraph number two.")]))
  =>
  "<body><p>This is paragraph number one.</p><p>This is paragraph number two.</p></body>")
