package mock;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;
import io.bettoni.metro.engine.Player;

public class MockedPlayer implements Player {

	private Character playerChar;

	public MockedPlayer(Character playerChar) {
		this.playerChar = playerChar;
	}

	@Override
	public Character getCharacter() {
		return playerChar;
	}

	@Override
	public GameInput makeAMovement(GameBoard currentState) {
		return null;
	}

}
