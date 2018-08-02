package io.bettoni.metro.engine.player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import io.bettoni.metro.engine.ia.GameIntelligenceAgent;

public class ComputerPlayerTest {

	@Mock
	private GameIntelligenceAgent agent;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_call_IAAgent_when_do_a_moviment() {
		ComputerPlayer player = new ComputerPlayer('X', agent);
		player.makeAMovement(null);
		Mockito.verify(agent, VerificationModeFactory.only()).createNextMove(null);
	}
}
