# yahoo-finance

An easy to use Clojure library for querying with the Yahoo Finance API

## Usage

```clojure

;; In project.clj:

[yahoo-finance-api "0.1.2"]

;; In ns statement:

(ns my.ns
(:require [yahoo-finance-api.core :refer :all])

;; Getting last price from the api:

(get-stock "googl")

;; returns {:last-price 730.22M}

;; Getting other fields from the api:

(get-stock "googl" :name :last-price :open :change)

;; returns {:name "Alphabet Inc", :last-price 730.22M, :open 734.32M, :change "1.37"}

## License

Distributed under the Eclipse Public License 1.0.
