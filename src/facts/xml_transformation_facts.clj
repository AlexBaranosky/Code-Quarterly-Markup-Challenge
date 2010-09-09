(ns facts.xml-transformation-facts
  (:use xml-transformation)
  (:use node)
  (:use midje.sweet))

;01_empty.txt
(fact
  (to-xml (body []))
  =>
  "<body/>")

;02_simple_paragraph.txt
(fact
  (to-xml (body (p "This is a simple paragraph.")))
  =>
  "<body><p>This is a simple paragraph.</p></body>")

;04_two_paragraphs.txt
(fact
  (to-xml (body [(p "This is paragraph number one.") (p "This is paragraph number two.")]))
  =>
  "<body><p>This is paragraph number one.</p><p>This is paragraph number two.</p></body>")

;06_header.txt
(fact
  (to-xml (body (h1 "This is a top level header")))
  =>
  "<body><h1>This is a top level header</h1></body>")
