package io.bettoni.metro.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.bettoni.metro.Application;
import io.bettoni.metro.engine.Player;
import io.bettoni.metro.engine.ia.RandomIntelligenceAgent;
import io.bettoni.metro.engine.player.ComputerPlayer;
import io.bettoni.metro.engine.player.HumanPlayer;

public class ConfigurationHelper {

	private static final String HUMAN_TYPE = "human";
	private static final String CONFIG_FILE = "app.config";

	public static GameConfiguration createGameConfiguration() throws IOException {
		Properties properties = loadPropertiesFile();

		String boardSize = properties.getProperty("board.size");
		evaluateProperty(boardSize);

		String numberOfPlayers = properties.getProperty("number.of.players");
		evaluateProperty(numberOfPlayers);

		return new GameConfiguration(Integer.parseInt(boardSize),
				createPlayers(Integer.parseInt(numberOfPlayers), properties));

	}

	private static List<Player> createPlayers(int numberOfPlayers, Properties properties) {
		List<Player> players = new ArrayList<>();

		for (int actualConfiguration = 1; actualConfiguration <= numberOfPlayers; actualConfiguration++) {
			String type = properties.getProperty(String.format("player.%d.type", actualConfiguration));
			String character = properties.getProperty(String.format("player.%d.character", actualConfiguration));

			if (HUMAN_TYPE.equalsIgnoreCase(type)) {
				players.add(new HumanPlayer(character.charAt(0)));
				continue;
			}

			players.add(new ComputerPlayer(character.charAt(0), new RandomIntelligenceAgent()));

		}

		return players;
	}

	private static void evaluateProperty(String property) {
		if (property == null)
			throw new IllegalArgumentException(String.format("Property \"%s\" should be informed.", property));
	}

	private static Properties loadPropertiesFile() throws IOException {
		InputStream configFileStream = null;
		try {
			configFileStream = Application.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			if (configFileStream == null) {
				throw new IllegalArgumentException("File not found: " + CONFIG_FILE);
			}

			Properties settings = new Properties();
			settings.load(configFileStream);

			return settings;
		} finally {
			if (configFileStream != null) {
				try {
					configFileStream.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
}
