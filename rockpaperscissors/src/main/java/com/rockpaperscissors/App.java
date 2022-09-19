package com.rockpaperscissors;

public class App 
{
    public static void main( String[] args )
    {
        String[] optionsA = {"paper"};
        String[] optionsB = {"rock", "paper", "scissors"};

        //Creating player A and player B
        Player playerA = new Player("Player A", optionsA);
        Player playerB = new Player("Player B", optionsB);
        Match rockpaperscissorsMatch = new Match(playerA, playerB, 100, 31, 37, 32);

        rockpaperscissorsMatch.startMatch();
    }
}
