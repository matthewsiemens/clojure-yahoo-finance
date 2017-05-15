(ns yahoo-finance-api.core
  (:require [yahoo-finance-api.api :as api]))

(defn get-stock [symbol & field-keys]
  (if (not field-keys)
    (api/get-stock-from-api symbol (list :last-price))
    (api/get-stock-from-api symbol field-keys)))
