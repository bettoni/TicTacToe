package io.bettoni.metro.engine;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.bettoni.metro.configuration.GameConfiguration;
import mock.MockedPlayer;

public class GameStateTest {

	private GameState gameState;

	@Before
	public void setUp() {
		gameState = new GameState(createValidConfiguration(3, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_start_game_with_null_configuration() {
		GameState gameState = new GameState(null);
		gameState.startNewGame();

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_if_board_size_is_greater_than_10() {
		gameState = new GameState(createValidConfiguration(11, 1));
		gameState.startNewGame();

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_if_board_size_is_smaller_than_3() {
		gameState = new GameState(createValidConfiguration(2, 1));
		gameState.startNewGame();

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_if_players_were_not_informed() {
		gameState = new GameState(createValidConfiguration(3, 0));
		gameState.startNewGame();

	}


	@Test
	public void should_return_running_status_when_start_a_game() {
		gameState.startNewGame();
		Assert.assertEquals(GameStatus.RUNNING, gameState.getStatus());
	}

	@Test
	public void should_win_the_game_with_three_cells_filled_in_a_row() {
		gameState.startNewGame();

		gameState.play(new GameInput(0, 0));
		gameState.play(new GameInput(0, 1));
		gameState.play(new GameInput(0, 2));

		Assert.assertEquals(GameStatus.WIN, gameState.getStatus());
	}

	@Test
	public void should_win_the_game_with_three_cells_filled_in_a_column() {
		gameState.startNewGame();

		gameState.play(new GameInput(0, 0));
		gameState.play(new GameInput(1, 0));
		gameState.play(new GameInput(2, 0));

		Assert.assertEquals(GameStatus.WIN, gameState.getStatus());
	}

	@Test
	public void should_win_the_game_with_three_cells_filled_in_a_diagonal() {
		gameState.startNewGame();

		gameState.play(new GameInput(0, 2));
		gameState.play(new GameInput(1, 1));
		gameState.play(new GameInput(2, 0));

		Assert.assertEquals(GameStatus.WIN, gameState.getStatus());
	}

	@Test
	public void should_win_the_game_with_three_cells_filled_in_a_right_diagonal() {
		gameState.startNewGame();

		gameState.play(new GameInput(0, 0));
		gameState.play(new GameInput(1, 1));
		gameState.play(new GameInput(2, 2));

		Assert.assertEquals(GameStatus.WIN, gameState.getStatus());
	}

	@Test
	public void should_tie_the_game_when_board_is_full_filled() {
		gameState = new GameState(createValidConfiguration(3, 3));
		gameState.startNewGame();

		for (int row = 0; row < gameState.getBoard().getSize(); row++) {
			gameState.play(new GameInput(row, 0));
			gameState.play(new GameInput(row, 1));
			gameState.play(new GameInput(row, 2));

		}

		Assert.assertEquals(GameStatus.DRAW, gameState.getStatus());
	}

	private GameConfiguration createValidConfiguration(int boardSize, int numberOfPlayers) {
		List<Player> players = createPlayers(numberOfPlayers);
		return new GameConfiguration(boardSize, players);
	}

	private List<Player> createPlayers(int numberOfPlayers) {
		List<Player> players = new ArrayList<>();

		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new MockedPlayer((char) Character.getType(i + 32)));
		}

		return players;
	}
}
