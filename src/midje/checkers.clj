(ns midje.checkers)

(defn truthy 
  "Returns precisely true if actual is not nil and not false."
  [actual] 
  (not (not actual)))

(defn falsey 
  "Returns precisely true if actual is nil or false."
  [actual] 
  (not actual))

(defn in-any-order
  "Produces matcher that matches sequences without regard to order"
  [expected]
  (fn [actual] 
      (and (= (count expected) (count actual))
	   (= (set expected) (set actual)))))

(defn anything
  "Accepts any value"
  [actual]
  true)

(defn exactly
  "Checks for equality. Use to avoid default handling of functions."
  [expected]
  (fn [actual] (= expected actual)))
