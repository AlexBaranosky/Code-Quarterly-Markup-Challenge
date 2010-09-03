(ns common)

(defn txt [file-sans-extension]
  (str file-sans-extension ".txt"))

(defn xml [file-sans-extension]
  (str file-sans-extension ".xml"))

(defn zip [c1 c2]
  (map list c1 c2))

(defn resource [f]
  (str "C:\\Users\\Alex and Paula\\Documents\\Software Projects\\CodeChallengeMarkup\\resources\\" f))