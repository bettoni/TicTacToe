package io.bettoni.metro.configuration;

import java.util.List;

import io.bettoni.metro.engine.Player;

public class GameConfiguration {

	private int boardSize;

	private List<Player> players;

	public GameConfiguration(int boardSize, List<Player> list) {
		this.boardSize = boardSize;
		this.players = list;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public List<Player> getPlayers() {
		return players;
	}

}
