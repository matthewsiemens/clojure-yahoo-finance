(ns yahoo-finance-api.formatter)

(defn format-numeric-value [field-value]
  (bigdec (.replaceAll field-value "[^0-9.]" "")))

(defn format-text-value [field-value]
  (.replaceAll field-value "[^A-Za-z ]" ""))

(defn format-mixed-value [field-value]
  (.replaceAll field-value "[^0-9.+% ]" ""))

(defn format-date-value [field-value]
  (.replaceAll field-value "[^0-9/]" ""))

(defn format-time-value [field-value]
  (.replaceAll field-value "[^0-9:(pm|am)]" ""))

(defn format-value [value type]
  (case type
    "text" (format-text-value value)
    "numeric" (format-numeric-value value)
    "mixed" (format-mixed-value value)
    "date" (format-date-value value)
    "time" (format-time-value value)
    value
    )
  )