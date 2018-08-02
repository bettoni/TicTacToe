package io.bettoni.metro;

import java.io.IOException;
import java.util.Scanner;

import io.bettoni.metro.client.TerminalGameRunner;
import io.bettoni.metro.configuration.ConfigurationHelper;

public class Application {

	public static void main(String[] args) {

		try {
			new TerminalGameRunner(ConfigurationHelper.createGameConfiguration(), new Scanner(System.in), System.out).run();

		} catch (IOException e) {
			System.out.println("Fail to load the configuration!");
			System.out.println("The application will be terminated");
			System.exit(-1);
		}
	}
}