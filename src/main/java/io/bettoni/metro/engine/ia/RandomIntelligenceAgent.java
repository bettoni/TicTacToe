package io.bettoni.metro.engine.ia;

import java.util.Collections;
import java.util.List;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;

public class RandomIntelligenceAgent implements GameIntelligenceAgent {

	@Override
	public GameInput createNextMove(GameBoard board) {
		List<GameInput> availableCells = board.getAvailableCells();

		if (availableCells.isEmpty()) {
			return null;
		}

		Collections.shuffle(availableCells);
		return availableCells.get(0);
	}
}
