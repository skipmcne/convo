process.greet.X(agent).Y(agent)
    action "Greet"
    preconditions
    // They must be co-located
    	X.in!L and Y.in!L
    postconditions
    	text "[X] says ’Hi’ to [Y obj]"
    	update_conversation.L.Actor.Y.respond_to_greet
    	insert process.respond_to_greet.Y.X
    	delete self
end
