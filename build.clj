#!/usr/bin/env bb

(require '[babashka.fs :as fs]
         '[babashka.process :refer [shell]])

(println "JIT")

(shell "javac" "Repro.java")
(shell "java" "Repro")

(println "native-image")
(shell "native-image" "-H:EnableURLProtocols=http,https,jar,unix" "Repro")
(shell (if (fs/windows?)
         "./repro.exe"
         "./repro"))
