package com.rockpaperscissors;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
// import static org.mockito.Mockito.*;

public class MatchTest{
    Player playerA;
    Player playerB;
    Match match;
    String[] handsignOptionsA = {"paper"};
    String[] handsignOptionsB = {"rock", "paper", "scissors"}; 

    @Before
    public void setUp() throws Exception {
        playerA = new Player("Player A", handsignOptionsA);
        playerB = new Player("Player B", handsignOptionsB);
        match = new Match(playerA, playerB, 10, 2, 3, 5);
    }

    @Test
    public void testSelectChoiceWithInput() {
        assertEquals("paper", match.selectChoice(playerA));
    }

}