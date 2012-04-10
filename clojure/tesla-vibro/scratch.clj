(ns user
  (:require (tesla-vibro [prosthetic :as p]
                         [audio :as a])))

(use 'overtone.live)

(p/doit "list-weavrs/")
(p/doit "get-location/10/")

(odoc demo)
(odoc after-delay)

(odoc saw)

(def s (synth (saw 220)))

(keys s)

(def i (s))

(odoc record-buf)

(def b (buffer 44100 1))

(def x (record-buf (saw 440) b :action FREE :loop 0))
;; x is a ugen.

b
s
x
i
(kill i)

(odoc synth)

(definst foo [] (saw 220))
(foo)
(kill foo)

(defsynth foo [] (saw 200))
(def id (foo))
(kill id)

(def m (metronome 120))

(m 1)

(demo (saw 200))

(odoc apply-at)

(apply-at (+ (now) 2000)
          #(demo (saw 220)))

(odoc midi-in)

(def fooble (midi-in))

(start-recording)

(meta #'recording-start)

(meta #'record-buf)

(odoc record-buf)

(stop)

(defsynth foo [] (pan2 (saw 220)))

;;--- Attempt top-level recording

(definst foo [] (pan2 (saw 220)))

(:ugens foo)

(do (recording-start "~/Desktop/gonko.wav")
    (foo))

(status)
(node-tree)

(do
  (kill foo)
  (recording-stop))

;;--- Record audio hit to buffer

(def b (buffer 44100 1))

(definst bong [note 60 velocity 0.5 attack 0.01 decay 1]
  (let [freq (midicps note)
        src (+ (sin-osc freq)
               (* 0.5 (sin-osc (* 2.1 freq)))
               (* 0.4 (sin-osc (* 4.9 freq)))
               (* 0.3 (sin-osc (* 7.1 freq)))
               (* 0.2 (sin-osc (* 8.9 freq)))
               (* 0.1 (square (* 1.3 freq)))
               (* 0.1 (square (* 4.2 freq))))
        env (env-gen (perc attack decay) :action FREE)]
    (record-buf (* velocity src env) (:id b) :action FREE :loop 0)))

(bong)

(buffer-save b "~/Desktop/grooble.wav")

(:doc (meta #'record-buf))
(:doc (meta #'buffer-save))

b

(kill 3)

;;---

(:doc (meta #'env-gen))

(env-gen:kr (perc 0 1))

(at (+ (now) 1000) (demo (saw 200)))

(def b (buffer 44100))
(save-buffer b ("~/Desktop/foo.wav"))

(status)

(meta #'saw)

(meta #'status)

(meta #'buffer)

(keys record-buf)

(odoc after-delay)

(after-delay 1000 #(println "A"))

(def s (synth (saw 220)))

(def id (s))

(kill id)


(a/generate "garble")

;; NO
(def b (buffer 44100 1))
(def bu (record-buf (saw 440) b :action FREE :loop 0))
(def syn (synth bu))
(syn)
(buffer-save b "~/Desktop/garble.wav")

;; YES
(def b (buffer 44100 1))
(def syn (synth (record-buf (saw 440) b :action FREE :loop 0)))
(syn)
(buffer-save b "~/Desktop/garble.wav")


(def s (synth (out 0 (saw 220))))
(s)
(stop)


(def o (out 0 (saw 220)))
(def s (synth o))
(s)


(out 0 (saw 220))