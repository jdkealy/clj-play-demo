(ns sidescreen.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]
            [clj-http.client :as client]
            [clojure.core.async :as async :refer [chan close! >!! <!! thread alts!! go >! <!]]
            [cheshire.core :refer :all]
            [clj-http.client :as client]
            ))

(def c (chan))

(def uniq-id (atom 50))
(defn get-next [] (swap! uniq-id inc))

(defn move
  [entity direction]
  (case direction
    :down (assoc entity :y (dec (:y entity)))
    :up (assoc entity :y (inc (:y entity)))
    :right (assoc entity :x (inc (:x entity)))
    :left (assoc entity :x (dec (:x entity)))
    nil))

(comment
  (get-next)
  (go (
       (let [res (<! c)]
         (println res))))
  )

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (go (>! c "SHOW"))
    (add-timer! screen :spawn-enemy 1 1 29)
    (update! screen :renderer (stage) :camera (orthographic))
    (texture "asset_two.jpg"
             :flip true false
             ))
  :on-resize
  (fn [screen entities]
    (height! screen 600))

  :on-timer
  (fn [screen entities]
    (go (>! c "AVR"))
    nil)
  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities))
  :on-key-down
  (fn [screen entities]
    (cond
      (= (:keycode screen) (key-code :dpad-down))
      (move (first entities) :down)
      (= (:keycode screen) (key-code :dpad-up))
      (move (first entities) :up)
      (= (:keycode screen) (key-code :dpad-right))
      (move (first entities) :right)
      (= (:keycode screen) (key-code :dpad-left))
      (move (first entities) :left))))

(defgame sidescreen
  :on-create
  (fn [this]
    (set-screen! this main-screen)))

(comment
  (on-gl (set-screen! sidescreen main-screen))
  (-> main-screen :entities deref)
  )
