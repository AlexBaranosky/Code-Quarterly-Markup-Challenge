(ns node-tests
  (:use midje.sweet)
  (:use markupparsing)
  (:use common)
  (:use node)
  (:use xmltransformation)
  (:use xml))

;;; common.clj

(fact
  (blank? "") => true)

(fact
  (blank? "                           ") => true)

(fact
  (blank? "

  ") => true)

(fact
  (blank? " goat ") => false)

(fact
  (blank? "
  goat

     ") => false)

(fact
  (parse "") => (make-node :body nil))

(fact
  (split-non-blank-chunks "groom-self  abc
  def

  ghi

  paula balla") => ["groom-self  abc
  def" "ghi" "paula balla"])

(fact
  (split-non-blank-chunks "123

  abc") => ["123" "abc"])

(fact
  (multi-line? "") => false)

(fact
  (multi-line? " 123 abc") => false)

(fact
  (multi-line? " 123
  abc") => false)

(fact
  (multi-line? "123

  abc") => true)

;;; markupparsing.clj



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

(def two-paragraphs
  (make-node :body (list (make-node :p "This is paragraph number one.") (make-node :p "This is paragraph number two."))))

(fact
  (parse "This is paragraph number one.

This is paragraph number two.")
  =>
  two-paragraphs)

(fact
  (to-xml two-paragraphs)
  =>
  "<body><p>This is paragraph number one.</p><p>This is paragraph number two.</p></body>")

;;; node.clj

(fact
  (make-node :div [...child1... ...child2...]) =>
  (struct node :div [...child1... ...child2...]))



;"01_empty"
(fact
  (parse "") => (make-node :body))

;02_simple_paragraph
(fact (parse "This is a simple paragraph.") =>
       (make-node :body
         (list  (make-node :p "This is a simple paragraph."))))

