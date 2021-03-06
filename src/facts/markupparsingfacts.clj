(ns facts.markup-parsing-facts
  (:use markup-parsing)
  (:use token)
  (:use node)
  (:use midje.sweet))

(fact (take-first-token ["paragraph"]) => (paragraph-token ["paragraph"]))
(fact (remaining-text-blocks ["paragraph"]) => [])
(fact (remaining-text-blocks ["paragraph" "paragraph" "paragraph"]) => [])

(fact (take-first-token ["paragraph", "  blockquote", "  blockquote2"]) => (paragraph-token ["paragraph"]))
(fact (remaining-text-blocks ["paragraph", "  blockquote", "  blockquote2"]) =>  ["  blockquote", "  blockquote2"])

(fact (take-first-token ["* heading1", "* heading2", "paragraph"]) => (heading-token ["* heading1", "* heading2"]))
(fact (remaining-text-blocks ["paragraph", "  blockquote", "  blockquote2"]) =>  ["  blockquote", "  blockquote2"])
(fact (tokenize ["paragraph", "* heading1", "* heading2"]) => [(paragraph-token ["paragraph"]), (heading-token ["* heading1", "* heading2"])])

(fact (take-first-token ["  blockquote1", "  blockquote2", "paragraph"]) => (blockquote-token ["  blockquote1", "  blockquote2"]))
(fact (remaining-text-blocks ["  blockquote1", "  blockquote2", "paragraph"]) => ["paragraph"])


(fact (tokenize ["  blockquote1", "  blockquote2", "paragraph"])
  =>
  [(blockquote-token ["  blockquote1", "  blockquote2"]), (paragraph-token ["paragraph"])])

(fact (tokenize ["* heading1", "* heading2"])
  =>
  [(heading-token ["* heading1", "* heading2"])])

(fact (tokenize ["paragraph"]) => [(paragraph-token ["paragraph"])] )

(fact (parse-tokens [(paragraph-token ["paragraph"])]) => [(p "paragraph")])

;"01_empty"
(fact (parse "") => (body []))

;other
(fact (parse "hi") => (body (p "hi")))


;02_simple_paragraph
(fact (parse "This is a simple paragraph.") => (body (p "This is a simple paragraph.")))

;04_two_paragraphs
(fact (parse "This is paragraph number one.

This is paragraph number two.")
  =>
  (body [(p "This is paragraph number one.") (p "This is paragraph number two.")]))

;06_header
(fact (parse "* This is a top level header") => (body (h1 "This is a top level header")))

;07_headers
(fact (parse "* This is a primary header.

** This is a secondary header.

*** This is a tertiary header.")
  =>
  (body [(h1 "This is a primary header.") (h2 "This is a secondary header.") (h3 "This is a tertiary header.")]))

;09_headers_and_paragraphs
(fact (parse "* Header 1

paragraph 1

** Header 2

paragraph 2")
  =>
  (body [(h1 "Header 1") (p "paragraph 1") (h2 "Header 2") (p "paragraph 2")]))

;10_blockquotes
(fact (parse "  This is a blockquote paragraph") => (body [(blockquote (p "This is a blockquote paragraph"))]))

;11_multiple_blockquotes
(fact (parse "  This is a blockquote paragraph
  that spans multiple lines") => (body [(blockquote (p "This is a blockquote paragraph that spans multiple lines"))]))

;12_multiple_paragraph_blockquote
(fact (parse
"  blockquote
  1

  second

  third")
  =>
  (body (blockquote [(p "blockquote 1") (p "second") (p "third")])))

;13_paragraphs_and_blockquotes
(fact (parse
"alex
b

  bob
  c

cindy
d

  david
  e

  earl
  f

fred
g")
  =>
  (body
    [(p "alex b")
     (blockquote (p "bob c"))
     (p "cindy d")
     (blockquote [(p "david e") (p "earl f")])
     (p "fred g")]))
