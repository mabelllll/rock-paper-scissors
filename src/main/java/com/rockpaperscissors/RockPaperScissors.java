package main.java.com.rockpaperscissors;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissors {
    // static int numOfRounds = 100;
    // static int aWins = 31;
    // static int maxPaper = 32;
    // static int maxScissors = 37;
    static int rockCounter = 0;
    static int paperCounter = 0;
    static int scissorsCounter = 0;
    //static String[] handsignArr = {"rock", "paper", "scissors"};
    static String choiceA = "paper";
    static String choiceB = "";
    static String newChoiceB = "";
    static ArrayList<String> currentHandsignArrList = new ArrayList<>();
    static Random random = new Random();
    static int randomNum;
    
    public static void main(String[] args) throws Exception {

        Player playerA = new Player();
        Player playerB = new Player();
        Match rockpaperscissorsMatch = new Match();

        //Assign name to each player
        playerA.setName("Player A");
        playerB.setName("Player B");

        //Assign number of matches to be played (For this task: 100)
        rockpaperscissorsMatch.setNumOfMatches(4);

        //Assign options for handsigns
        String[] handsignArr = {"rock", "paper", "scissors"};
        rockpaperscissorsMatch.setHandsignArr(handsignArr);

        //Assign how many times A wins, B win and how many ties
        rockpaperscissorsMatch.setNumOfAWin(1);
        rockpaperscissorsMatch.setNumOfBWins(1);
        rockpaperscissorsMatch.setNumOfDraws(2);

        //Assign choice of each player
        String choiceA = "paper";
        String choiceB = rockpaperscissorsMatch.selectChoice(handsignArr);

        // int numOfMatches = rockpaperscissorsMatch.getNumOfMatches();

        rockpaperscissorsMatch.startMatch(rockpaperscissorsMatch.getNumOfMatches(), choiceA, choiceB);

    }
}
