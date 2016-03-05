(ns yahoo-finance-api.services.api-service
  (:require [clojure.string :as str]
            [clojure-csv.core :as csv]
            [yahoo-finance-api.services.formatter-service :as formatter]))

(def api-uri
  "http://download.finance.yahoo.com/d/quotes.csv?s=%s&f=%s")

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

(defn convert-keywords-to-api-params
  [field-keys-list]
  (str/join (map :api-param (map api-field-mappings field-keys-list)))
  )

(defn build-api-call
  [symbol fields]
  (format api-uri symbol fields)
  )

(defn get-response-from-api
  [symbol field-keys]
  (first (csv/parse-csv (slurp (build-api-call symbol (convert-keywords-to-api-params field-keys)))))
  )

(defn get-type
  [value]
  (let [type (:type value)]
    type)
  )

(defn get-stock-from-api
  [symbol field-keys]
  (let [types (map get-type (map api-field-mappings field-keys))
        api-response (get-response-from-api symbol field-keys)]
    (zipmap field-keys (map formatter/format-value api-response types))
    ))