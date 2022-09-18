package main.java.com.rockpaperscissors;
import java.util.ArrayList;
import java.util.Random;

public class Match {
    private int numOfMatches;
    private int numOfAWins;
    private int numOfDraws;
    private int numOfBWins;
    private int aWinsCounter = 0;
    private int bWinsCounter = 0;
    private int drawsCounter = 0;
    private String[] handsignArr = {};
    private ArrayList<String> handSignArrList = new ArrayList<>();
    private Random random = new Random();
    private int randomNum;
    private String newChoiceB;
    private ArrayList<String> currentHandsignArrList = new ArrayList<>();

    public String[] getHandsignArr() {
        return this.handsignArr;
    }
    public ArrayList<String> getHandsignArrList() {
        return this.handSignArrList;
    }

    public void setHandsignArr(String[] handSignArr) {
        for(int i = 0; i < handSignArr.length; i++) {
            this.handSignArrList.add(handSignArr[i]);
        }
    }

    public int getNumOfMatches() {
        return this.numOfMatches;
    }

    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }

    public int getNumOfAWin() {
        return numOfAWins;
    }

    public void setNumOfAWin(int numOfAWins) {
        this.numOfAWins = numOfAWins;
    }

    public int getNumOfDraws() {
        return numOfDraws;
    }

    public void setNumOfDraws(int numOfDraws) {
        this.numOfDraws = numOfDraws;
    }

    public int getNumOfBWins() {
        return numOfBWins;
    }

    public void setNumOfBWins(int numOfBWins) {
        this.numOfBWins = numOfBWins;
    }

    // Generate random choice for player B
    public String selectChoice(String[] arr) {
        randomNum = random.nextInt(arr.length);
        // String selectedChoice = handsignArr[randomNum];
        // String[] temp = getHandsignArr();
        String selectedChoice = arr[randomNum];

        return selectedChoice;
    }
    
    // Regenerate new random choice for player B
    public String reselectChoice(ArrayList<String> handsignArrList) {
        String reselectedChoice = "";
        if (handsignArrList.size() == 1) {
            reselectedChoice = handsignArrList.get(0);
        } else {
            randomNum = random.nextInt(handsignArrList.size());
            reselectedChoice = handsignArrList.get(randomNum);
        }

        return reselectedChoice;
    }

    // Generate new array list with new options of handsign
    public ArrayList<String> createHandsignArrList(String oldChoiceB, String[] handsignArr) {
        this.currentHandsignArrList.clear();
        for(int i = 0; i < handsignArr.length; i++) {
            if (!handsignArr[i].equals(oldChoiceB)) {
                this.currentHandsignArrList.add(handsignArr[i]);
            }
        }

        return this.currentHandsignArrList;
    }

    // Regenerate new array list with new options of handsign after fail choice of first new array list generated
    public ArrayList<String> createHandsignArrList(String oldChoiceB, ArrayList<String> handsignArrList) {
        int index = handsignArrList.indexOf(oldChoiceB);
        handsignArrList.remove(index);
        this.currentHandsignArrList = handsignArrList;

        return this.currentHandsignArrList;
    }

    // Recomparing choices selected from new generated array list of handsign options
    public void recompareChoices(String choiceA, String reselectedChoice) {
        if(reselectedChoice.equals("rock")) {
            if(this.aWinsCounter < this.numOfAWins) {
                this.aWinsCounter++;
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, this.currentHandsignArrList);
                this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                recompareChoices(choiceA, this.newChoiceB);
            }
        } else if (newChoiceB.equals("paper")) {
            if(this.drawsCounter < this.numOfDraws) {
                this.drawsCounter++;
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, this.currentHandsignArrList);
                this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                recompareChoices(choiceA, this.newChoiceB);
            }
        } else if (newChoiceB.equals("scissors")) {
            if(this.bWinsCounter < this.numOfBWins) {
                this.bWinsCounter++;
                results(choiceA, reselectedChoice);
            } else {
                createHandsignArrList(reselectedChoice, this.currentHandsignArrList);
                this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                recompareChoices(choiceA, this.newChoiceB);
            }
        }
    }

    // Print out results of each match
    public void results(String choiceA, String choiceB) {
        String strB = "";
        System.out.println("A's choice: " + choiceA);
        if(this.newChoiceB.equals("")) {
            System.out.println("B's choice: " + choiceB);
            strB = choiceB;
        } else {
            System.out.println("B's choice: " + this.newChoiceB);
            strB = this.newChoiceB;
        }
        switch(strB){
            case "rock":
                System.out.println("Player A wins!");
                break;
            case "paper":
                System.out.println("It's a tie!");
                break;
            case "scissors":
                System.out.println("Player B wins!");
                break;
            default:
                System.out.println("Invalid choice.");
                break;

        }
        System.out.println();
    }

    // To start the game
    public void startMatch(int numOfMatches, String choiceA, String choiceB) {
        for (int i = 1; i <= numOfMatches; i++) {
            //String choiceB = selectChoice(handsignArr);
            newChoiceB = "";
            System.out.println("Round " + i);
            if(choiceB.equals("rock")) {
                if(this.aWinsCounter < this.numOfAWins) {
                    this.aWinsCounter++; 
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, this.handsignArr);
                    this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                    recompareChoices(choiceA, this.newChoiceB);
                }
            } else if(choiceB.equals(choiceA)) {
                if(this.drawsCounter < this.numOfDraws) {
                    this.drawsCounter++;
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, this.handsignArr);
                    this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                    recompareChoices(choiceA, this.newChoiceB);
                }
            } else if (choiceB.equals("scissors")) {
                if(this.bWinsCounter < this.numOfBWins) {
                    this.bWinsCounter++;
                    results(choiceA, choiceB);
                } else {
                    this.currentHandsignArrList = createHandsignArrList(choiceB, this.handsignArr);
                    this.newChoiceB = reselectChoice(this.currentHandsignArrList);
                    recompareChoices(choiceA, this.newChoiceB);
                }
            }
        }
        System.out.println("Below are the total amount of each choices made by player B.");
        System.out.println("Rock: " + aWinsCounter);    
        System.out.println("Paper: " + drawsCounter);
        System.out.println("Scissors: " + bWinsCounter);
        int total = aWinsCounter + drawsCounter + bWinsCounter;
        System.out.println("rock + paper + scissors = " + total);
    }
}
