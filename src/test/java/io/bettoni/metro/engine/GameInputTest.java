package io.bettoni.metro.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameInputTest {

	private GameInput leftSide;

	@Before
	public void setUp() {
		leftSide = new GameInput(0, 0);
	}

	@Test
	public void equals_is_false_when_parameter_is_null() {
		Assert.assertFalse(leftSide.equals(null));
	}

	@Test
	public void equals_is_false_when_parameter_is_not_a_game_input() {
		Assert.assertFalse(leftSide.equals(""));
	}

	@Test
	public void equals_is_false_when_row_parameter_is_different() {
		GameInput rightSide = new GameInput(1, 0);

		Assert.assertFalse(leftSide.equals(rightSide));
	}

	@Test
	public void equals_is_false_when_column_parameter_is_different() {
		GameInput rightSide = new GameInput(0, 1);

		Assert.assertFalse(leftSide.equals(rightSide));
	}

	@Test
	public void equals_is_true_when_parameter_is_equal() {
		GameInput rightSide = new GameInput(0, 0);

		Assert.assertTrue(leftSide.equals(rightSide));
	}

}
