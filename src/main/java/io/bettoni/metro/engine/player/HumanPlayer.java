package io.bettoni.metro.engine.player;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;
import io.bettoni.metro.engine.Player;

public class HumanPlayer implements Player {

	private Character character;
	private GameInput userInput;

	public HumanPlayer(char character) {
		this.character = character;
	}

	@Override
	public Character getCharacter() {
		return character;
	}

	@Override
	public GameInput makeAMovement(GameBoard board) {
		if (board.getAvailableCells().contains(userInput)) {
			return userInput;
		}

		return null;
	}

	@Override
	public String toString() {
		return String.format("Player %s", character);
	}

	public void addUserEntry(GameInput userInput) {
		this.userInput = userInput;
	}

}
