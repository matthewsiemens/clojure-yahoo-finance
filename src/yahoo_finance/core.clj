(ns yahoo-finance.core
  (:require [clojure.string :as str]
            [clojure-csv.core :as csv]))

(def api-field-mappings
  {
   :name {:api-param "n" :type "text"}
   :last-price {:api-param "l1" :type "numeric"}
   :ask {:api-param "a" :type "numeric"}
   :bid {:api-param "b" :type "numeric"}
   :prev-close {:api-param "c" :type "mixed"}
   :open {:api-param "o" :type "numeric"}
   :change {:api-param "c1" :type "mixed"}
   :change-and-percent {:api-param "c" :type "mixed"}
   :change-percent {:api-param "p2" :type "mixed"}
   :last-trade-date {:api-param "d1" :type "date"}
   :last-trade-time {:api-param "t1" :type "time"}
   }
  )

(defn get-fields
  [field-keys-list]
  (str/join (map :api-param (map api-field-mappings (map keyword field-keys-list))))
  )

(defn build-api-call [symbol fields]
  (format "http://download.finance.yahoo.com/d/quotes.csv?s=%s&f=%s" symbol fields)
  )

(defn get-response-from-api [symbol field-keys]
  (first (csv/parse-csv (slurp (build-api-call symbol (get-fields field-keys)))))
  )

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

(defn get-type [value]
  (let [type (:type value)]
    type)
  )

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

(defn do-stuff [symbol field-keys]
  (let [keywords (map keyword field-keys)
        types (map get-type (map api-field-mappings (map keyword field-keys)))
        api-response (get-response-from-api symbol field-keys)]
    (zipmap keywords (map format-value api-response types))
    ))

(defn get-stock-from-api [symbol & field-keys]
  (if (not field-keys)
    (do-stuff symbol (list "last-price"))
    (do-stuff symbol field-keys)
    )
  )