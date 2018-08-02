package io.bettoni.metro.engine;

public class GameInput {

	private int row;
	private int column;

	public GameInput(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GameInput) || obj == null) {
			return false;
		}
		GameInput input = (GameInput) obj;

		return this.getRow() == input.getRow() && this.getColumn() == input.getColumn();
	}

}
