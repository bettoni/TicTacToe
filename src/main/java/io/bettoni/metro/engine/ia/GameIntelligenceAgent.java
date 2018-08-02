package io.bettoni.metro.engine.ia;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;

public interface GameIntelligenceAgent {

	GameInput createNextMove(GameBoard board);
}
