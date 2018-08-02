package io.bettoni.metro.engine.player;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;
import io.bettoni.metro.engine.Player;
import io.bettoni.metro.engine.ia.GameIntelligenceAgent;

public class ComputerPlayer implements Player {

	private Character character;

	private GameIntelligenceAgent intelligenceAgent;

	public ComputerPlayer(Character character, GameIntelligenceAgent agent) {
		this.character = character;
		this.intelligenceAgent = agent;
	}

	@Override
	public Character getCharacter() {
		return character;
	}

	@Override
	public GameInput makeAMovement(GameBoard currentState) {
		return intelligenceAgent.createNextMove(currentState);
	}

	@Override
	public String toString() {
		return String.format("Computer %s", character);
	}
}
