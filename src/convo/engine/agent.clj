; action overrides
; emotional state
(ns convo.engine.agent
 "agent"
 )

(s/def ::first-name string?)
(s/def ::first-name string?)
(s/def ::last-name string?)

(s/def ::agent (s/keys :req [::first-name ::last-name]
                        :opt [::phone]))

(s/def ::agent)


; update agents opinion of other Actors 
(defn observeActor 	[Agent facts]
)
; update agents knowlege of the world 
(defn observeFacts 	[Agent facts]
)

; pick an action from the list of actions to do 
(defn filterActions [Agent actions]
)

; make a new character 
(defmacro char [charid describe greet askedNPC askedLoKnow]

)

;
(defmacro AskLocalKnowledge [KnowStmtList]
		(EvaluateStmtList NPCStmtList)
)
;
(defmacro AskedAboutNPC [NPCStmtList]
	(EvaluateStmtList NPCStmtList)
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