; action overrides
; emotional state
(ns convo.engine.agent
 "agent / actor / character"
  (:require [clojure.spec.alpha :as spec])
 )

(spec/def ::topic keyword?) ;; easiest for now, need to make discoverable
(spec/def ::fact string?) ;; easiest for now, figure out detail (how to use in sentences) later
(spec/def ::stmt string?)
(spec/def ::confidence int?)

(spec/def ::kbelief (spec/keys :req [::topic ::fact ::stmt ::confidence])) 
(spec/def ::kthing  (spec/keys :req [::topic ::fact ::stmt ::confidence]))
(spec/def ::kperson (spec/keys :req [::topic ::fact ::stmt ::confidence])) 

(spec/def ::kbeliefs (spec/coll-of [::kbelief]))
(spec/def ::kthings  (spec/coll-of [::kthing]))
(spec/def ::kpeople  (spec/coll-of [::kperson]))

(spec/def ::knowledge (spec/keys :req [::kbeliefs ::kthings ::kpeople ])) 
;; 
(spec/def ::appearance (spec/coll-of [::kbelief]))
(spec/def ::reputation (spec/coll-of [::kbelief]))
(spec/def ::firstName string?)
(spec/def ::lastName string?)
(spec/def ::age int?)
(spec/def ::description (spec/keys :req [::firstName ::lastName ::age ::appearance ::reputation ])) ; map??
;;
;;
(spec/def ::dex int?)
(spec/def ::str int?)
(spec/def ::con int?)
(spec/def ::int int?)
(spec/def ::wis int?)
(spec/def ::cha int?)
(spec/def ::abilities (spec/keys :req [::str ::dex ::con ::int ::wis ::cha]))
;;
(spec/def ::acrobatics int?)
(spec/def ::arcana int?)
(spec/def ::athletics int?)
(spec/def ::bluff int?)
(spec/def ::diplomacy int?)
(spec/def ::dungeoneering int?)
(spec/def ::endurance int?)
(spec/def ::heal int?)
(spec/def ::history int?)
(spec/def ::insight int?)
(spec/def ::intimidate int?)
(spec/def ::nature int?)
(spec/def ::perception int?)
(spec/def ::religion int?)
(spec/def ::stealth int?)
(spec/def ::streetwise int?)
(spec/def ::thievery int?)
;;
(spec/def ::skills    (spec/keys :req [::acrobatics ::arcana ::athletics ::bluff ::diplomacy ::dungeoneering ::endurance ::heal ::history ::insight ::intimidate ::nature ::perception ::religion ::stealth ::streetwise ::thievery]))
;;
(spec/def ::phy int?)
(spec/def ::mag int?)
(spec/def ::env int?)
(spec/def ::defenses  (spec/keys :req [::phy ::mag ::env]))
;;
(spec/def ::daze int?)
(spec/def ::blind int?)
(spec/def ::prone int?)
(spec/def ::disad int?)
(spec/def ::diseased int?)
(spec/def ::negStatus (spec/keys :req [::dazed ::diseased ::disad ::prone ::blind]))
;;
(spec/def ::aid int?)
(spec/def ::bless int?)
(spec/def ::benStatus (spec/keys :req [::aid ::bless ]))
;(spec/def ::saves :req [::phy ::mag ::env])
;;
(spec/def ::emotions #{:amusement :contempt :contentment :embarrassment :excitement :guilt :pride :relief :satisfaction :pleasure :shame})
(spec/def ::amt int?)
(spec/def ::emotion (spec/coll-of {::emotions ::amt}))

(spec/def ::actor (spec/keys
 :req [::actorId ::description ::abilities ::skills ::defenses ::negStatus ::benStatus  ::emotion ::knowledge]
 :opt [::localNPC ::localKnowlege ]
  ))


(defn defKBelief [topicId factCategory statement conf]
	(let [b1 {::topic topicId ::fact factCategory ::stmt statement ::confidence conf}]
		(if (spec/valid? ::kbelief b1)
			b1
			nil
			)))
;(def b2 (defBelief :monestary "location" "the StoneWard Monestary is outside town" 90))
;(def b1 (defBelief :monestary "haunted" "the Monestary is Haunted" 80))


(defn defKThing [topicId factCategory statement conf]
	(let [b1 {::topic topicId ::fact factCategory ::stmt statement ::confidence conf}]
		(if (spec/valid? ::kthing b1)
			b1
			nil
			)))


(defn defKPerson [topicId factCategory statement conf]
	(let [b1 {::topic topicId ::fact factCategory ::stmt statement ::confidence conf}]
		(if (spec/valid? ::kperson b1)
			b1
			nil
			)))


(defn addknowledgePerson [knowBaseMap topicId factCategory statement conf]
	(let [knowp (defKPerson topic factCategory statement conf)]
		(if (!= nil knowp)
			 (assoc knowBaseMap (get knowp ::topic) knowp)
		)
	)
)


(defn addknowledgeThings [knowBaseMap topicId factCategory statement conf]
	(let [knowp (defKThing topic factCategory statement conf)]
		(if (!= nil knowp)
			 (assoc knowBaseMap (get knowp ::topic) knowp)
		)
	)
)


(defn addbelief [knowBaseMap topicId factCategory statement conf]
	(let [knowp (defBelief topic factCategory statement conf)
		  knowBaseMap]
		(if (!= nil knowp)
			 (assoc knowBaseMap (get knowp ::topic) knowp)
		)
	)
)


; make a new character 
(defn char [charid describe greet askedNPC askedLoKnow]
	(let [desc ({::firstName "" ::lastName  "" ::age 0 ::appearance {} ::reputation {}})
		  abil ({::str 10 ::dex 10 ::con 10 ::int 10 ::wis 10 ::cha 10})
		  skilmap ({::acrobatics 0 ::arcana 0 ::athletics 0 ::bluff 0 ::diplomacy 0 ::dungeoneering 0 ::endurance 0 ::heal 0 ::history 0  ::insight 0 ::intimidate 0  ::nature 0 ::perception 0 ::religion 0 ::stealth 0 ::streetwise 0 ::thievery 0 })
		  dfenmap ({::phy 0 ::mag 0 ::env 0 })
		  nstatusMap ({::dazed 0 ::diseased 0 ::disad 0 ::prone 0 ::blind 0})
		  bstatusMap ({::aid 0 ::bless 0})
		  emotion ({:amusement  0 :contempt  0 :contentment  0 :embarrassment  0 :excitement  0 :guilt  0 :pride  0 :relief  0 :satisfaction 0 :pleasure  0 :shame 0})
		  knowledgeBase ({:kbeliefs {} ::kthings {} ::kpeople {}})]
	     (def actor {::actorId charid ::description desc ::abilities abil  ::skills skilmap ::defenses dfenmap ::negStatus nstatusMap ::benStatus bstatusMap  ::emotion emotion ::knowledge knowledgeBase})
	     )
)


; update agents opinion of other Actors 
(defn observeActor 	[Agent facts]
)


; update agents knowlege of the world 
(defn observeFacts 	[Agent facts]
)


; pick an action from the list of actions to do 
(defn filterActions [Agent actions]
)
 

(defn say [character stmt]
	(println (get character :firstName) "said" stmt)
)


;
(defmacro AskLocalKnowledge [KnowStmtList]
		(EvaluateStmtList NPCStmtList)
)


;
(defmacro AskedAboutNPC [NPCStmtList]
	(EvaluateStmtList NPCStmtList)
)


(defn DCRoll [charactor ability die]
	(rand-int die)
)


(defmacro EvaluateStmtList [stmtList]
	(let [defaultStmt (first stmtList)
		  DCstmtList (rest stmtList)
		  ;DCRoll (DCRoll Actor  20) ; need modifier / whatever
		  ]
	 (do 
	    ;evaluate default "say"
	    ;DC roll
	    ;then evlauate DCStmtList
	    )
	)
)

