(ns sidescreen.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]
            ))

(defn move
  [entity direction]
  (case direction
    :down (assoc entity :y (dec (:y entity)))
    :up (assoc entity :y (inc (:y entity)))
    :right (assoc entity :x (inc (:x entity)))
    :left (assoc entity :x (dec (:x entity)))
    nil))

(defscreen main-screen
  :on-timer
  (fn [screen entities]
    (assoc (texture "clojure.png")
           :x 350 :y 250 ))
  :on-show
  (fn [screen entities]
    (add-timer! screen :spawn-enemy 1)
    (update! screen :renderer (stage) :camera (orthographic))
    (assoc (texture "clojure.png")
           :x 150 :y 150 ))
  :on-resize
  (fn [screen entities]
    (height! screen 600))

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
