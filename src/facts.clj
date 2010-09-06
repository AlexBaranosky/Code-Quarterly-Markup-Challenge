(ns node-tests
  (:use midje.sweet)
  (:use markupparsing)
  (:use common)
  (:use node)
  (:use xmltransformation)
  (:use xml))

;;; xml.clj

(fact
  (start-tag "div") => "<div>")

(fact
  (end-tag "div") => "</div>")

(fact
  (self-closing-tag "br") => "<br/>")

;;; xml-transformation.clj

(fact
  (to-xml (make-node :body nil)) => "<body></body>")

(fact
  (to-xml
    (make-node :body
      (make-node :p "This is a simple paragraph.")))
  =>
  "<body><p>This is a simple paragraph.</p></body>")

;;; node.clj

(fact
  (make-node :div [...child1... ...child2...]) =>
  (struct node :div [...child1... ...child2...]))

