(ns xmlidx.core
  (:require [clojure.java.io :as io]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.data.xml :as dxml]))

(defn file-xml-events [name]
  (-> name
      io/file
      io/input-stream
      dxml/source-seq))

(defn index-text-path-simple
  ([events] (index-text-path-simple events []))
  ([events path]
   (lazy-seq
    (when-not (empty? events)
      (let [event (first events)
            type (:type event)]
        (case type
          :start-element (index-text-path-simple (rest events) (conj path (:name event)))
          :end-element (index-text-path-simple (rest events) (pop path))
          (cons path (index-text-path-simple (rest events) path))))))))

(defn index-text-path-arrays
  ([events] (index-text-path-arrays events [] [{}]))
  ([events path siblings]
   (lazy-seq
    (when-not (empty? events)
      (let [event (first events)
            type (:type event)]
        (case type
          :start-element (let [name (:name event)]
                           (index-text-path-arrays (rest events) (conj path name)
                                                   (conj
                                                    (conj (pop siblings)
                                                          (update (last siblings) name
                                                                  #(inc (or % 0))))
                                                    {})))
          :end-element (index-text-path-arrays (rest events) (pop path)
                                               (pop siblings))
          (cons (map (fn [s n] [n (get s n)])
                     siblings path)
                (index-text-path-arrays (rest events) path siblings))))))))
