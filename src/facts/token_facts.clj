(ns facts.token-facts
  (:use token)
  (:use node)
  (:use midje.sweet))

(fact (parse-blockquotes (blockquote-token ["  bq1"])) => [(blockquote [(p "bq1")])])
(fact (parse-blockquotes (blockquote-token ["  bq1" "  bq2" "  bq3"])) => [(blockquote [(p "bq1") (p "bq2") (p "bq3")])])
(fact (parse-headings (heading-token ["* 1" "* 2" "* 3"])) => [(h1 "1") (h1 "2") (h1 "3")])
(fact (parse-paragraphs (paragraph-token ["1" "2" "3"])) => [(p "1") (p "2") (p "3")])
(fact (parse-paragraphs (paragraph-token ["1"])) => [(p "1")])

(fact (parse-verbatims (verbatim-token ["   verbatim"])) => [(pre "verbatim")])
