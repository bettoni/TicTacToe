package io.bettoni.metro.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mock.MockedPlayer;

public class GameBoardTest {

	private static final int DEFAULT_BOARD_SIZE = 3;
	private GameBoard board;
	private GameInput mockedInput;
	private Player mockedPlayer;

	@Before
	public void setUp() {
		board = new GameBoard(DEFAULT_BOARD_SIZE);
		mockedInput = getMockedInput();
		mockedPlayer = getMockedPlayer();
	}

	@Test
	public void should_create_a_square_board() {
		Assert.assertEquals(DEFAULT_BOARD_SIZE, board.getBoard().length);
		Assert.assertEquals(DEFAULT_BOARD_SIZE, board.getBoard()[0].length);
	}

	@Test
	public void should_return_value_in_a_specific_position() {
		board.fillPosition(mockedInput, mockedPlayer);

		Assert.assertEquals(mockedPlayer.getCharacter().toString(),
				board.getValue(mockedInput.getRow(), mockedInput.getColumn()));
	}

	@Test
	public void should_return_all_available_empty_cells() {
		board.fillPosition(mockedInput, mockedPlayer);

		Assert.assertEquals(8, board.getAvailableCells().size());
	}

	@Test
	public void should_return_null_when_try_to_get_value_off_the_bord() {
		Assert.assertNull(board.getValue(-1, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_fill_with_null_value() {
		board.fillPosition(null, mockedPlayer);
	}

	private Player getMockedPlayer() {
		return new MockedPlayer('X');
	}

	private GameInput getMockedInput() {
		return new GameInput(1, 2);
	}

}
