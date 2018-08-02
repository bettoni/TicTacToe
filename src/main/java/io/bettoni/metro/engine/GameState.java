package io.bettoni.metro.engine;

import java.util.Collections;
import java.util.List;

import io.bettoni.metro.configuration.GameConfiguration;

public class GameState {

	private GameStatus status;
	private int currentPlayerIndex;
	private GameBoard board;
	private List<Player> players;
	private Player currentPlayer;
	private GameConfiguration configuration;

	public GameState(GameConfiguration configuration) {
		this.configuration = configuration;
	}

	public void startNewGame() {
		validateConfiguration();

		this.board = new GameBoard(configuration.getBoardSize());

		this.currentPlayerIndex = 0;
		this.players = configuration.getPlayers();
		Collections.shuffle(players);
		this.currentPlayer = players.get(currentPlayerIndex);

		this.status = GameStatus.RUNNING;
	}

	private void validateConfiguration() {
		if (configuration == null) {
			throw new IllegalArgumentException("Configuration can not be null");
		}

		if (configuration.getBoardSize() < 3 || configuration.getBoardSize() > 10) {
			throw new IllegalArgumentException("Board size must be between 3 and 10");
		}

		if (configuration.getPlayers() == null || configuration.getPlayers().isEmpty()) {
			throw new IllegalArgumentException("Players must be informed");
		}
	}

	public GameStatus getStatus() {
		return status;
	}

	public Player nextPlayer() {
		return currentPlayer;
	}

	public GameBoard play(GameInput gameInput) {
		board.fillPosition(gameInput, currentPlayer);

		if (isBoardFilled()) {
			status = GameStatus.DRAW;
			return board;
		}

		if (isItAWinningPlay(gameInput)) {
			status = GameStatus.WIN;
			return board;
		}

		currentPlayer = players.get(nextPlayerIndex());
		return board;
	}

	private boolean isBoardFilled() {
		return board.getAvailableCells().isEmpty();
	}

	public GameBoard getBoard() {
		return board;
	}

	private int nextPlayerIndex() {
		currentPlayerIndex++;
		if (currentPlayerIndex >= players.size()) {
			currentPlayerIndex = 0;
		}

		return currentPlayerIndex;
	}

	private boolean isItAWinningPlay(GameInput gameInput) {
		int neededCells = 3;
		int column = 0;
		int row = 0;
		int diagonal = 0;
		int rdiagonal = 0;
		boolean winner = false;

		for (int currentCell = 0; currentCell < board.getSize(); currentCell++) {

			if (currentPlayer.getCharacter().toString().equals(board.getValue(gameInput.getRow(), currentCell))) {
				column++;
			} else {
				column = 0;
			}

			if (currentPlayer.getCharacter().toString().equals(board.getValue(currentCell, gameInput.getColumn()))) {
				row++;
			} else {
				row = 0;
			}

			if (currentPlayer.getCharacter().toString().equals(board.getValue(currentCell, currentCell))) {
				diagonal++;
			} else {
				diagonal = 0;
			}

			if (currentPlayer.getCharacter().toString()
					.equals(board.getValue(currentCell, board.getSize() - currentCell - 1))) {
				rdiagonal++;
			} else {
				rdiagonal = 0;
			}

			if (row == neededCells || column == neededCells || diagonal == neededCells || rdiagonal == neededCells) {
				winner = true;
			}
		}

		return winner;
	}
}
