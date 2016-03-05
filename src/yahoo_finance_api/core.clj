(ns yahoo-finance-api.core
  (:require [yahoo-finance-api.services.api-service :as api]))

(defn get-stock [symbol & field-keys]
  (if (not field-keys)
    (api/get-stock-from-api symbol (list "last-price"))
    (api/get-stock-from-api symbol field-keys)
    )
  )