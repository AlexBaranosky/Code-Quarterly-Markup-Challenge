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
(fact (parse-verbatims (verbatim-token ["   verbatim1" "   verbatim2"])) => [(pre "verbatim1\n\nverbatim2")])
(fact (parse-verbatims (verbatim-token ["   verbatim1" "    verbatim2"])) => [(pre "verbatim1\n\n verbatim2")])
(fact (parse-verbatims (verbatim-token ["     verbatim1" "verbatim2"])) => [(pre "  verbatim1\n\nverbatim2")])

(fact (parse-ordered-lists (ordered-list-token ["  # ordered list"])) => [(ol (li (p "ordered list")))])

(fact (parse-ordered-lists (ordered-list-token ["  # 1", "    2"])) => [(ol (li [(p "1") (p "2")]))])
(fact (parse-ordered-lists (ordered-list-token ["  # 1", "    2", "  # 3", "    4"])) => [(ol [(li [(p "1") (p "2")]) (li [(p "3") (p "4")])])])

(fact (parse-unordered-lists (unordered-list-token ["  - unordered list"])) => [(ul (li (p "unordered list")))])

(fact (list-item-sections ["  # 1", "  # 2", "    3"]) => [["  # 1"] ["  # 2" "    3"]])