(ns common)

(defn resource [f]
  (str "C:\\Users\\Alex and Paula\\Documents\\Software Projects\\CodeChallengeMarkup\\resources\\" f))

(defn txt-resource [file-sans-extension]
  (resource (str file-sans-extension ".txt")))

(defn xml-resource [file-sans-extension]
  (resource (str file-sans-extension ".xml")))

(defn zip [c1 c2]
  (map list c1 c2))