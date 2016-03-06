(ns yahoo-finance-api.api-test
  (:require [clojure.test :refer :all]
            [yahoo-finance-api.api :refer :all]))

(deftest test-convert-keywords-to-api-params
  (testing "Test that we are converting keywords to Yahoo API Params successfully"
    (is (= "n" (convert-keywords-to-api-params '(:name))))
    (is (= "l1" (convert-keywords-to-api-params '(:last-price))))
    (is (= "a" (convert-keywords-to-api-params '(:ask))))
    (is (= "b" (convert-keywords-to-api-params '(:bid))))
    (is (= "c" (convert-keywords-to-api-params '(:prev-close))))
    (is (= "o" (convert-keywords-to-api-params '(:open))))
    (is (= "c1" (convert-keywords-to-api-params '(:change))))
    (is (= "c" (convert-keywords-to-api-params '(:change-and-percent))))
    (is (= "p2" (convert-keywords-to-api-params '(:change-percent))))
    (is (= "d1" (convert-keywords-to-api-params '(:last-trade-date))))
    (is (= "t1" (convert-keywords-to-api-params '(:last-trade-time))))
    (is (= "nl1ab" (convert-keywords-to-api-params '(:name :last-price :ask :bid))))
    ))

(deftest test-build-api-call
  (testing "Test that we are building up the API URI successfully"
    (is (= "http://download.finance.yahoo.com/d/quotes.csv?s=googl&f=n" (build-api-call "googl" "n")))
    ))

(deftest test-get-fields-types
  (testing "Test that we are converting keywords to field type successfully"
    (is (= '("text") (get-fields-types '(:name))))
    (is (= '("numeric") (get-fields-types '(:last-price))))
    (is (= '("numeric") (get-fields-types '(:ask))))
    (is (= '("numeric") (get-fields-types '(:bid))))
    (is (= '("mixed") (get-fields-types '(:prev-close))))
    (is (= '("numeric") (get-fields-types '(:open))))
    (is (= '("mixed") (get-fields-types '(:change))))
    (is (= '("mixed") (get-fields-types '(:change-and-percent))))
    (is (= '("mixed") (get-fields-types '(:change-percent))))
    (is (= '("date") (get-fields-types '(:last-trade-date))))
    (is (= '("time") (get-fields-types '(:last-trade-time))))
    (is (= '("text" "numeric" "numeric" "numeric") (get-fields-types '(:name :last-price :ask :bid))))
    ))

(deftest test-build-response
  (testing "Test that are merging the three lists together successfully"
    (is  (= {:last-price 730M} (build-response ["730"] '(:last-price) '("numeric"))))
    (is  (= {:last-price 730M :ask 733.06M, :bid 721.66M}
            (build-response ["730" "733.06" "721.66"] '(:last-price :ask :bid) '("numeric" "numeric" "numeric"))))
    ))