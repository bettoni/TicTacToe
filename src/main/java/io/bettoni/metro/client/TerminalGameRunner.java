package io.bettoni.metro.client;

import java.io.PrintStream;
import java.util.Scanner;

import io.bettoni.metro.configuration.GameConfiguration;
import io.bettoni.metro.engine.GameBoard;
import io.bettoni.metro.engine.GameInput;
import io.bettoni.metro.engine.GameState;
import io.bettoni.metro.engine.GameStatus;
import io.bettoni.metro.engine.Player;
import io.bettoni.metro.engine.player.HumanPlayer;

public class TerminalGameRunner implements GameRunner {

	private GameConfiguration gameConfiguration;
	private Scanner scanner;
	private PrintStream printStream;
	private GameState gameState;

	public TerminalGameRunner(GameConfiguration gameConfiguration, Scanner scanner, PrintStream printStream) {
		this.gameConfiguration = gameConfiguration;
		this.scanner = scanner;
		this.printStream = printStream;
	}

	@Override
	public void run() {
		printStream.println("Welcome do TicTacMetro");
		settingUpGame();
		printBoard(gameState.getBoard());

		while (gameState.getStatus() == GameStatus.RUNNING) {
			Player nextPlayer = gameState.nextPlayer();

			printStream.println(String.format("Next player is: %s", nextPlayer));

			if (nextPlayer instanceof HumanPlayer) {
				playerTurn(nextPlayer);
				printBoard(gameState.getBoard());
				continue;
			}

			gameState.play(nextPlayer.makeAMovement(gameState.getBoard()));
			printBoard(gameState.getBoard());

		}

		printStream.println(printEndMessage());
	}

	private String printEndMessage() {
		if (gameState.getStatus() == GameStatus.DRAW) {
			return "It's a TIE!!!!";
		}
		return String.format("The winner is: %s", gameState.nextPlayer());
	}

	private void playerTurn(Player nextPlayer) {
		HumanPlayer humanPlayer = (HumanPlayer) nextPlayer;
		boolean validPlay = false;
		do {
			printInstructions(nextPlayer);

			try {
				humanPlayer.addUserEntry(parseTerminalInput(scanner.nextLine()));
				gameState.play(humanPlayer.makeAMovement(gameState.getBoard()));
				validPlay = true;

			} catch (IllegalArgumentException e) {
				printStream.println(e.getMessage());
			}

		} while (!validPlay);

	}

	private void printBoard(GameBoard board) {
		BoardPrinterHelper.printBoard(board, printStream);
	}

	private void printInstructions(Player nextPlayer) {
		printStream.println(
				String.format("%s, enter 'row,column' to choose a position. For eg. '0,1'", gameState.nextPlayer()));
	}

	private GameInput parseTerminalInput(String playerInput) throws IllegalArgumentException {
		String[] inputs = playerInput.split(",");

		if (inputs.length != 2) {
			throw new IllegalArgumentException("Invalid number of arguments");
		}

		try {
			int row = Integer.parseInt(inputs[0]);
			int column = Integer.parseInt(inputs[1]);
			return new GameInput(row, column);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid value", e);
		}

	}

	private void settingUpGame() {
		gameState = new GameState(gameConfiguration);
		gameState.startNewGame();
	}
}
