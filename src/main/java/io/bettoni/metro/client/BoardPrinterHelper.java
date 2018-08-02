package io.bettoni.metro.client;

import java.io.PrintStream;

import io.bettoni.metro.engine.GameBoard;

public class BoardPrinterHelper {

	private BoardPrinterHelper() {
	}

	public static void printBoard(GameBoard board, PrintStream printStream) {
		String printContent = "";
		for (int row = 0; row < board.getBoard().length; row++) {
			printContent += "|";
			for (int column = 0; column < board.getBoard()[row].length; column++) {
				String value = board.getValue(row, column) == null ? " " : board.getValue(row, column);
				printContent += value + "|";
			}
			printContent += "\n";
		}

		printStream.println(printContent);
	}

}
