package com.rockpaperscissors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MatchTest{
    
    public String[] handsignOptionA;
    public String[] handsignOptionB; 
    public Player playerA;
    public Player playerB;
    public Match match;
    public ArrayList<String> handsignOptions;

    @BeforeEach
    public void setUp() {
        handsignOptionA = new String[]{"paper"};
        handsignOptionB = new String[]{"rock", "paper", "scissors"};
        playerA = new Player("Player A", handsignOptionA);
        playerB = new Player("Player B", handsignOptionB);
        match = new Match(playerA, playerB, 10, 2, 3, 5);
        handsignOptions = new ArrayList<>();
    }

    @Test
    //Testing if selected choice of handsign is the first element when there's only one element in array
    public void selectChoice_FromSingleHandsignOption() {
        assertEquals(playerA.getHandsignOptions().get(0), match.selectChoice(playerA));
    }

    @Test
    //Testing if selected choice of handsign is one of the element in the array
    public void selectChoice_FromMultipleHandsignOptions() {
        assertTrue(playerB.getHandsignOptions().contains(match.selectChoice(playerB)));
    }

    @Test
    //Testing if the reselected choice is is not the same as the old selected choice
    public void reselectChoice() {
        String selectChoice = match.selectChoice(playerB);
        ArrayList<String> newHandsignOptionsArrList = match.createHandsignArrList(selectChoice, playerB.getHandsignOptions());
        assertNotEquals(match.reselectChoice(newHandsignOptionsArrList), selectChoice);
    }

    @Test
    //Testing if new handsign array list has one element lesser than generated new array list
    public void createHandsignArrList() {
        handsignOptions = playerB.getHandsignOptions();
        assertFalse(match.createHandsignArrList("paper", handsignOptions).contains("paper"));
    }

    @Test
    //Testing if a tie, return value is "It's a tie"
    public void defineWinner_matchIsATie() {
        assertEquals("It's a tie!", match.defineWinner("paper", "paper"));
        assertEquals("It's a tie!", match.defineWinner("rock", "rock"));
        assertEquals("It's a tie!", match.defineWinner("scissors", "scissors"));
        assertNotEquals("It's a tie!", match.defineWinner("paper", "rock"));
    }

    @Test
    //Testing if player A wins, return value is "Player A wins!"
    public void defineWinner_playerAWins() {
        assertEquals(playerA.getName() + " wins!", match.defineWinner("rock", "scissors"));
    }

    @Test
    //Testing if player B wins, return value is "Player B wins!"
    public void defineWinner_playerBWins() {
        assertEquals(playerB.getName() + " wins!", match.defineWinner("paper", "scissors"));
    }

    @Test
    //Testing if number of matches is the sum of number of A won, number of B loses and number of draws
    public void matchResult() {
        match.startMatch();
        int numOfAWon = match.getPlayerA().getNumOfWins();
        int numOfBWon = match.getPlayerB().getNumOfWins();
        assertEquals(match.getNumOfMatches(), numOfAWon + numOfBWon + match.getPlayerA().getNumOfDraws());
        assertEquals(match.getPlayerA().getNumOfWins(), match.getPlayerB().getNumOfLoses());
        assertEquals(match.getPlayerB().getNumOfWins(), match.getPlayerA().getNumOfLoses());
        assertEquals(match.getPlayerA().getNumOfDraws(), match.getPlayerB().getNumOfDraws());
    }
}
