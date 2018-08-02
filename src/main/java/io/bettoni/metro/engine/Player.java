package io.bettoni.metro.engine;

public interface Player {

	Character getCharacter();

	GameInput makeAMovement(GameBoard currentState);
}
