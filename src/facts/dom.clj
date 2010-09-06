(ns facts.dom
  (:import (java.io InputStream ByteArrayInputStream))
  (:import (org.w3c.tidy Tidy))
  (:import (org.custommonkey.xmlunit XMLUnit))
  (:import (junit.framework Assert)))

(defn configure-xml-unit []
  (XMLUnit/setIgnoreAttributeOrder true)
  (XMLUnit/setIgnoreWhitespace true))

(def *tidy*
  (do
    (configure-xml-unit)
    (doto (Tidy.)
      (.setShowErrors 0)
      (.setShowWarnings false)
      (.setQuiet true))))

(defn- parse-dom [xml-string]
  (let [input-stream (ByteArrayInputStream. (.getBytes xml-string))]
    (.parseDOM *tidy* input-stream nil)))

(defn generate-diff [control test]
  (let [control-dom (parse-dom control)
        test-dom (parse-dom test)]
    (XMLUnit/compareXML control-dom test-dom)))

(defn assert-doms-equal [expected actual]
  (let [diff (generate-diff expected actual)]
    (if (not (.identical diff))
      (Assert/assertEquals (str "DOMs not equal: " diff) expected actual))))

