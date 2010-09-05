(ns acceptancetest
  (:use clojure.test)
  (:use dom)
  (:use markupparsing)
  (:use node)
  (:use xmltransformation)
  (:use common))

(def example-files ["01_empty"
                    "02_simple_paragraph"
                    "03_multiline_paragraph"
                    "04_two_paragraphs"
                    "05_several_multiline_paragraphs"
                    "06_header"
                    "07_headers"
                    "08_crazy_header"
                    "09_headers_and_paragraphs"
                    "10_blockquote"
                    "11_multiline_blockquote"
                    "12_multiparagraph_blockquote"
                    "13_paragraphs_and_blockquotes"
                    "14_simple_verbatim"
                    "15_useful_verbatim"
                    "16_verbatim_with_indentation"
                    "17_verbatim_first_line_extra_indented"
                    "18_verbatim_special_xml_chars"
                    "19_numbered_list"
                    "20_bulleted_list"
                    "21_multiparagraph_list_items"
                    "22_nested_lists"
                    "23_tagged_markup"
                    "24_note_subdocument"
                    "25_multiparagraph_note"
                    "26_note_with_blockquote"
                    "27_note_with_lists"
                    "28_required_escapes"
                    "29_optional_escapes"
                    "30_escaped_header"
                    "31_escaped_numbered_list_marker"
                    "32_escaped_bullet_list_marker"
                    "33_escapes_not_needed"
                    "34_modeline"])

(defn- assert-txt-parses-to-xml [txt-file xml-file]
  (let [expected-xml (slurp xml-file)
        actual-xml (to-xml (parse-file txt-file))]
    (println (str "Expected: " expected-xml))
    (println (str "Actual: " actual-xml))
    (assert-doms-equal expected-xml actual-xml)))

(defn acceptance-test []
  (let [txts (map txt-resource example-files)
        xmls (map xml-resource example-files)]
    (doseq [pair (zip txts xmls)]
      (assert-txt-parses-to-xml (first pair) (second pair)))))

(acceptance-test)

(is (= (parse "") (make-node :body)))
;
;(def child1 0)
;(def child2 0)
;(is (= (make-node :div child1 child2) (struct node :div [child1 child2])))
;
;(is (= (parse-file (txt "01_empty")) (make-node :body)))
;
;(is (= (parse "This is a simple paragraph.") (make-node :body (make-node :p (make-node :text "This is a simple paragraph.")))))
