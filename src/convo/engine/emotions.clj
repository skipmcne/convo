(ns convo.engine.emotions
 "emotions"
 (:require [clojure.spec.alpha :as s])
 )

;; emotional state of a character contains :

(s/def ::emotions #{:amusement :contempt :contentment :embarrassment :excitement :guilt :pride :relief :satisfaction :pleasure :shame})
(s/def ::pos-int-lt100 (s/and int? #(> % 0) #(< % 101)))
;(s/def ::emotion { :emotions :pos-int-lt100})
(s/def ::emotion (s/keys :req [::emotions ::pos-intlt100]))
; Contempt,
; Contentment,
; Embarrassment,
; Excitement,
; Guilt,
; Pride in achievement,
; Relief,
; Satisfaction,
; Sensory pleasure,
; Shame

;
;;;;;;
;; These can be felt at differing levels [integer]
;; They decay over time
;; Emotional state can drive "portrait changes", or other ways of displaying to players
;; 
;;
;;;;;;