(ns cursive-ns-test.db)

(def default-db
  {:name "re-frame"})

(comment
  ;; This is where I'm missing the require suggestion (even if this is outside a comment.)
  (log/info :msg "foo"))