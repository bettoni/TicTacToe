package io.bettoni.metro.engine;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

	private String board[][];
	private int size;

	public GameBoard(int size) {
		this.size = size;
		this.board = new String[size][size];
	}

	public void fillPosition(GameInput gameInput, Player currentPlayer) {
		validatePosition(gameInput);
		board[gameInput.getRow()][gameInput.getColumn()] = currentPlayer.getCharacter().toString();
	}

	public int getSize() {
		return size;
	}

	public String[][] getBoard() {
		return board;
	}

	public String getValue(int row, int column) {
		if (isOffTheBoard(row, column)) {
			return null;
		}

		return board[row][column];
	}

	public List<GameInput> getAvailableCells() {
		List<GameInput> availableInputs = new ArrayList<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (getValue(i, j) == null) {
					availableInputs.add(new GameInput(i, j));
				}
			}
		}

		return availableInputs;
	}

	private void validatePosition(GameInput gameInput) {
		if (gameInput == null) {
			throw new IllegalArgumentException("Invalid position");
		}
	}

	private boolean isOffTheBoard(int row, int column) {
		return row < 0 || row >= size || column < 0 || column >= size;
	}

}
