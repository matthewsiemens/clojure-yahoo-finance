# yahoo-finance

An easy to use Clojure library for querying with the Yahoo Finance API

[![Build Status](https://travis-ci.org/matthewsiemens/clojure-yahoo-finance.svg?branch=master)](https://travis-ci.org/matthewsiemens/clojure-yahoo-finance)
[![Dependency Status](https://www.versioneye.com/user/projects/56ea0ee04e714c003625c3c8/badge.svg)](https://www.versioneye.com/clojure/yahoo-finance-api:yahoo-finance-api/)
[![Clojars Project](https://img.shields.io/clojars/v/yahoo-finance-api.svg)](https://clojars.org/yahoo-finance-api)

## Usage

```clojure

;; In project.clj:
[yahoo-finance-api "0.1.6"]

;; clojure-yahoo-finance v0.1.6 uses clojure-csv 2.0.2

;; In ns statement:
(ns my.ns
(:require [yahoo-finance-api.core :refer :all])

```

## Getting Stock

```clojure

;; Getting last price from the api:
(get-stock "googl")
;; returns {:last-price 730.22M}

;; Getting other fields from the api:
(get-stock "googl" :name :last-price :open :change)
;; returns {:name "Alphabet Inc", :last-price 730.22M, :open 734.32M, :change "1.37"}

```

## Available Stock Fields

- :name
- :last-price
- :ask
- :bid
- :prev-close
- :open
- :change
- :change-and-percent
- :change-percent
- :last-trade-date
- :last-trade-time
- :one-year-target-price
- :52-week-low-price
- :52-week-high-price
- :volume
- :last-trade-size
- :market-cap-realtime
- :shares-outstanding
- :book-value
- :ebitda
- :pe-ratio-realtime

## ToDo

This library is very usable but still could use a little TLC. I'm hoping to have the following added shortly.

- Allow getting more than one stock in a single request
- Better error handling

## License

Copyright © 2016 Matthew Siemens

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
