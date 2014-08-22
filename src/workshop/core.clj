(ns workshop.core
  (:import [java.util Random])
  (:use [clojure.xml :as xml]))



(def seconds 1000)
(def minutes (* 60 seconds))
(def new-data-interval (* 10 seconds))

(def vehicles [:audi :volvo :skoda :tesla :unicorn
               :ferrari :ford :lancia :tvr :tank])

(def col-military [:green :greige :beige])
(def col-magic [:rainbow])
(def col-meh [:beige :greige :black :brown])
(def col-norm [:red :green :blue :white])
(def col-strong [:lime-green :yellow :orange :purple])
(def col-pearl [:pearlescent-turquiose :pearlescent-magenta])


(defn current-seed [interval]
  (int (/ (System/currentTimeMillis) interval)))

(defn take-random [rnd n col]
  (if (= n 0)
    '()
    (let [item (nth col (.nextInt rnd (count col)))
          more (remove #(= % item) col)]
      (concat [item] (take-random rnd (dec n) more)))))

(defn gen-vehicles [rnd]
  (take-random rnd (max 1 (.nextInt rnd (count vehicles))) vehicles))

(defn available-colours [v]
  (condp = v
    :unicorn col-magic
    :tank col-military
    :tvr (concat col-pearl col-strong)
    :ferrari (concat col-norm col-strong)
    :lancia (concat col-norm col-strong)
    :audi [:white]
    :ford [:black]
    v (concat col-meh col-norm)))

(defn add-colours [v rnd]
  (let [cs (available-colours v)
        cols (take-random rnd (max 1 (.nextInt rnd (count cs))) cs)]
    (if (seq cols) [v cols])))

(defn gen-data [seed]
  (let [rnd (Random. seed)
        vs (gen-vehicles rnd)]
    (apply hash-map
      (mapcat add-colours vs (repeat rnd)))))

(defn go-xml [seed]
  (str "<vehicles>\n"
    (apply str
      (for [[k v] (gen-data seed)]
        (str "  <vehicle type=\"" (name k) "\">\n"
          (apply str (for [c v] (str "    <colour>" (name c) "</colour>\n")))
          "  </vehicle>\n")))
  "</vehicles>\n"))

(defn go []
  (go-xml (current-seed new-data-interval)))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (go)})


;  (cond
;    (= (request :uri) "/")
;    {:status 200
;     :headers {"Content-Type" "text/html"}
;     :body (my-page)}
;    (= (request :uri) "/data.handler")
;    (do
;      (handle (-> request :headers (get "data")))
;      {:status 200})
;    :else
;    {:status 418}))



; (def data (clojure.xml/parse "http://localhost:3000/data"))
; (for [each (:content data)] (str (:type (:attrs each)) " : " (clojure.string/join ", " (for [colour (:content each)] (first (:content colour))))))
