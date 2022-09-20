package com.rockpaperscissors;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MatchTest{
    
    String[] handsignOptionA = {"paper"};
    String[] handsignOptionB = {"rock", "paper", "scissors"}; 
    Player playerA = new Player("Player A", handsignOptionA);
    Player playerB = new Player("Player B", handsignOptionB);
    Match match = new Match(playerA, playerB, 10, 2, 3, 5);
    ArrayList<String> handsignOptions = new ArrayList<>();

    @Test
    public void testSelectChoiceFromSingleHandsignOption() {
        // If handsign array has only one element
        assertEquals(playerA.getHandsignOptions().get(0), match.selectChoice(playerA));
    }

    @Test
    public void selectChoiceFromMultipleHandsignOptions() {
        // If handsign array has more than one element
        // Expected result should be one of the element in the handsign array
        assertTrue(playerB.getHandsignOptions().contains(match.selectChoice(playerB)));
    }

    @Test
    public void reselectChoice() {
        // Expected new choice to not be the same as old choice after reaching threshold
        String selectChoice = match.selectChoice(playerB);
        ArrayList<String> newHandsignOptionsArrList = match.createHandsignArrList(selectChoice, playerB.getHandsignOptions());
        assertNotEquals(match.reselectChoice(newHandsignOptionsArrList), selectChoice);
    }

    @Test
    public void matchResult() {
        match.startMatch();
        int numOfAWon = match.getPlayerA().getNumOfWins();
        int numOfBWon = match.getPlayerB().getNumOfWins();
        assertEquals(match.getNumOfMatches() - numOfAWon - numOfBWon, match.getPlayerA().getNumOfDraws());
        assertEquals(match.getPlayerA().getNumOfWins(), match.getPlayerB().getNumOfLoses());
        assertEquals(match.getPlayerB().getNumOfWins(), match.getPlayerA().getNumOfLoses());
        assertEquals(match.getPlayerA().getNumOfDraws(), match.getPlayerB().getNumOfDraws());
    }
}