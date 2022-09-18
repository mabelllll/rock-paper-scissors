package main.java.com.rockpaperscissors;

public class RockPaperScissors {    
    public static void main(String[] args) throws Exception {
        String[] optionsA = {"paper"};
        String[] optionsB = {"rock", "paper", "scissors"};

        //Creating player A and player B
        Player playerA = new Player("Player A", optionsA);
        Player playerB = new Player("Player B", optionsB);
        Match rockpaperscissorsMatch = new Match(playerA, playerB, 20, 10, 4, 6);

        rockpaperscissorsMatch.startMatch();

    }
}
