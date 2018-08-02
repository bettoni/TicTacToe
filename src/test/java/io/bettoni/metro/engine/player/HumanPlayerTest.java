package io.bettoni.metro.engine.player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;

public class HumanPlayerTest {
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_return_null_when_board_is_full() {
		HumanPlayer player = new HumanPlayer('X');
		Assert.assertNull(player.makeAMovement(makeBoard()));
	}

	@Test
	public void should_return_user_input_when_board_is_not_full() {
		HumanPlayer player = new HumanPlayer('X');
		player.addUserEntry(new GameInput(1, 1));
		Assert.assertNotNull(player.makeAMovement(makeBoard()));
	}

	private GameBoard makeBoard() {
		GameBoard board = new GameBoard(3);
		return board;
	}
}
