(defproject sidescreen "0.0.1-SNAPSHOT"
  :description "FIXME: write description"

  :dependencies [[com.badlogicgames.gdx/gdx "1.0.0"]
                 [com.badlogicgames.gdx/gdx-backend-lwjgl "1.0.0"]
                 [com.badlogicgames.gdx/gdx-box2d "1.0.0"]
                 [com.badlogicgames.gdx/gdx-box2d-platform "1.0.0"
                  :classifier "natives-desktop"]
                 [com.badlogicgames.gdx/gdx-bullet "1.0.0"]
                 [com.badlogicgames.gdx/gdx-bullet-platform "1.0.0"
                  :classifier "natives-desktop"]
                 [com.badlogicgames.gdx/gdx-platform "1.0.0"
                  :classifier "natives-desktop"]
                 [org.clojure/clojure "1.6.0"]
                 [cheshire "5.3.1"]
                 [clj-http "0.9.1"]
                 [org.clojure/core.async "0.1.301.0-deb34a-alpha"]
                 [play-clj "0.3.1"]]

  :source-paths ["src" "src-common"]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [sidescreen.core.desktop-launcher]
  :main sidescreen.core.desktop-launcher)
