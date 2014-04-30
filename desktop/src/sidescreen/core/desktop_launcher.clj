(ns sidescreen.core.desktop-launcher
  (:require [sidescreen.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. sidescreen "sidescreen" 800 600)
  (Keyboard/enableRepeatEvents true))
