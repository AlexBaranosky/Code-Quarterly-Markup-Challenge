(ns domtests
  (:use dom)
  (:import (junit.framework Assert)))

(def link-href-name "<a href=\"aaa\" name=\"nnn\">CONTENT</a>")
(def link-name-href "<a name=\"nnn\" href=\"aaa\">CONTENT</a>")
(def link-name-href-w-br "<a name=\"nnn\" href=\"aaa\">CONTENT</a><br>")
(def link-name-href-w-whitespace "            <a name=\"nnn     \" href=\"aaa\">        \r\n\t       CONTENT</a>     <br>")
(def link-name-href-w-whitespace-inside-word "<a name=\"nnn\" href=\"aaa\">CON TENT</a><br>")
(def link-name-href-class "<a name=\"nnn\" href=\"aaa\" class=\"ccc\">CONTENT</a>")

(defmacro assert-doms [control-xml test-xml assertion]
  `(let [diff# (generate-diff ~control-xml ~test-xml)]
    (~assertion (.identical diff#))))

(defn assert-doms-identical [control-xml test-xml]
  (assert-doms control-xml test-xml Assert/assertTrue))

(defn assert-doms-not-identical [control-xml test-xml]
  (assert-doms control-xml test-xml Assert/assertFalse))

(assert-doms-identical link-href-name link-name-href)
(assert-doms-identical link-name-href-w-br link-name-href-w-whitespace)
(assert-doms-not-identical link-name-href-class link-name-href)
(assert-doms-not-identical link-name-href-w-br link-name-href-w-whitespace-inside-word)