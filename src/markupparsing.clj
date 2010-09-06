(ns markupparsing
  (:use node)
  (:use common.string))

(defn parse [s]
  (let [children
        (cond
          (blank? s)
          nil

          (.startsWith s "* ")
          (h1 (.substring s 2))

          :else
          (let [lines (split-non-blank-chunks s)]
            (map p lines)))]
    (body children)))
