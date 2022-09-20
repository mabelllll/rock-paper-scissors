package com.rockpaperscissors;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Exception;

public class Match {
    private int numOfMatches;
    private int numOfAWins;
    private int numOfDraws;
    private int numOfBWins;
    private int aWinsCounter = 0;
    private int bWinsCounter = 0;
    private int drawsCounter = 0;
    private Player playerA;
    private Player playerB;
    private Random random = new Random();
    private int randomNum;
    private String newChoiceB = "";
    private ArrayList<String> currentHandsignArrList = new ArrayList<>();

    public Match(Player playerA, Player playerB, int numOfMatches, int numOfAWins, int numOfBWins, int numOfDraws) {
        try {
            if (numOfMatches == numOfAWins + numOfBWins + numOfDraws) {
                this.numOfMatches = numOfMatches;
                this.numOfAWins = numOfAWins;
                this.numOfBWins = numOfBWins;
                this.numOfDraws = numOfDraws;
                this.playerA = playerA;
                this.playerB = playerB;
            }
        } catch (Exception e) {
            System.out.println("Invalid number of matches given.");
        }
    }

    public int getNumOfMatches() {
        return this.numOfMatches;
    }

    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }

    // Generate random choice for player B
    public String selectChoice(Player player) {
        String selectedChoice;
        if (player.getHandsignOptions().size() == 1) {
            selectedChoice = player.getHandsignOptions().get(0);
        } else {
            randomNum = random.nextInt(player.getHandsignOptions().size());
            selectedChoice = player.getHandsignOptions().get(randomNum);
        }
        return selectedChoice;
    }

    // Regenerate new random choice for player B with elimination of old choice in the options
    public String reselectChoice(ArrayList<String> handsignArrList) {
        String reselectedChoice;
        if (handsignArrList.size() > 1) {
            randomNum = random.nextInt(handsignArrList.size());
            reselectedChoice = handsignArrList.get(randomNum);
        } else {
            reselectedChoice = handsignArrList.get(0);
        }

        return reselectedChoice;
    }

    // Regenerate new array list with new options of handsign after fail choice of
    // first new array list generated
    public ArrayList<String> createHandsignArrList(String oldChoiceB, ArrayList<String> handsignArrList) {
        int index = handsignArrList.indexOf(oldChoiceB);
        handsignArrList.remove(index);
        this.currentHandsignArrList = handsignArrList;

        return this.currentHandsignArrList;
    }

    // Recomparing choices selected from new generated array list of handsign options
    public void recompareChoices(String choiceA, String reselectedChoice) {
        if (reselectedChoice.equals("rock")) {
            if (aWinsCounter < numOfAWins) {
                this.aWinsCounter++;
                playerA.increaseNumOfWins();
                playerB.increaseNumOfLoses();
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, currentHandsignArrList);
                this.newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        } else if (newChoiceB.equals("paper")) {
            if (drawsCounter < numOfDraws) {
                this.drawsCounter++;
                playerA.increaseNumOfDraws();
                playerB.increaseNumOfDraws();
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, currentHandsignArrList);
                this.newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        } else if (newChoiceB.equals("scissors")) {
            if (bWinsCounter < numOfBWins) {
                this.bWinsCounter++;
                playerA.increaseNumOfLoses();
                playerB.increaseNumOfWins();
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, currentHandsignArrList);
                this.newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        }
    }

    public String defineWinner(String choiceA, String choiceB) {
        String matchResult = "";
        if (choiceA.equals(choiceB)) {
            matchResult = "It's a tie!";
            return matchResult;
        } else {
            switch (choiceA) {
                case "rock":
                    if (choiceB.equals("paper")) {
                        matchResult = playerB.getName() + " wins!";
                    } else {
                        matchResult = playerA.getName() + " wins!";
                    }
                    return matchResult;
                case "paper":
                    if (choiceB.equals("rock")) {
                        matchResult = playerA.getName() + " wins!";
                    } else {
                        matchResult = playerB.getName() + " wins!";
                    }
                    return matchResult;
                case "scissors":
                    if (choiceB.equals("paper")) {
                        matchResult = playerA.getName() + " wins!";
                    } else {
                        matchResult = playerB.getName() + " wins!";
                    }
                    return matchResult;
                default:
                    return "Invalid choice.";
            }
        }
    }

    // Print out results of each match
    public void results(String choiceA, String choiceB) {
        boolean result;
        System.out.println(playerA.getName() + "'s choice: " + choiceA);
        if (newChoiceB.equals("")) {
            System.out.println(playerB.getName() + "'s choice: " + choiceB);
            result = true;
        } else {
            System.out.println(playerB.getName() + "'s choice: " + newChoiceB);
            result = false;
        }

        if (result) {
            System.out.println(defineWinner(choiceA, choiceB));
            System.out.println();
        } else {
            System.out.println(defineWinner(choiceA, newChoiceB));
            System.out.println();
        }
    }

    // To start the game
    public void startMatch() {
        for (int i = 1; i <= numOfMatches; i++) {
            String choiceA = selectChoice(playerA); // for now, always paper
            String choiceB = selectChoice(playerB);
            this.currentHandsignArrList = playerB.getHandsignOptions();
            System.out.println("Round " + i);
            if (choiceB.equals("rock")) {
                if (aWinsCounter < numOfAWins) {
                    this.aWinsCounter++;
                    playerA.increaseNumOfWins();
                    playerB.increaseNumOfLoses();
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, currentHandsignArrList);
                    this.newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            } else if (choiceB.equals(choiceA)) {
                if (drawsCounter < numOfDraws) {
                    this.drawsCounter++;
                    playerA.increaseNumOfDraws();
                    playerB.increaseNumOfDraws();
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, currentHandsignArrList);
                    this.newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            } else if (choiceB.equals("scissors")) {
                if (bWinsCounter < numOfBWins) {
                    this.bWinsCounter++;
                    playerA.increaseNumOfLoses();
                    playerB.increaseNumOfWins();
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, currentHandsignArrList);
                    this.newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            }
        }
        System.out.println(playerA.getName() + "'s statistics");
        System.out.println("Win: " + playerA.getNumOfWins());
        System.out.println("Loses: " + playerA.getNumOfLoses());
        System.out.println(playerB.getName() + "'s statistics");
        System.out.println("Win: " + playerB.getNumOfWins());
        System.out.println("Loses: " + playerB.getNumOfLoses());
        System.out.println("Number of draws between " + playerA.getName() + " and " + playerB.getName() + ": " + playerA.getNumOfDraws());
    }
}
