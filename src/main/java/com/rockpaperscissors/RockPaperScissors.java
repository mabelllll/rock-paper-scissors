import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissors {
    static int numOfRounds = 100;
    static int maxRocks = 31;
    static int maxPaper = 32;
    static int maxScissors = 37;
    static int rockCounter = 0;
    static int paperCounter = 0;
    static int scissorsCounter = 0;
    static String[] handsignArr = {"rock", "paper", "scissors"};
    static String choiceA = "paper";
    static String choiceB = "";
    static String newChoiceB = "";
    static ArrayList<String> currentHandsignArrList = new ArrayList<>();
    static Random random = new Random();
    static int randomNum;

    public static String selectChoice(String[] arr) {
        randomNum = random.nextInt(arr.length);
        String selectedChoice = handsignArr[randomNum];

        return selectedChoice;
    }

    public static ArrayList<String> handsignArrList(String oldChoiceB, String[] handsignArray) {
        currentHandsignArrList.clear();
        for(int i = 0; i < handsignArr.length; i++) {
            if (!handsignArr[i].equals(oldChoiceB)) {
                currentHandsignArrList.add(handsignArr[i]);
            }
        }

        return currentHandsignArrList;
    }

    public static ArrayList<String> handsignArrList(String oldChoiceB, ArrayList<String> handsignArrayList) {
        int index = handsignArrayList.indexOf(oldChoiceB);
        handsignArrayList.remove(index);
        currentHandsignArrList = handsignArrayList;

        return currentHandsignArrList;
    }

    public static String reselectChoice(ArrayList<String> handsignArrList) {
        String reselectedChoice;
        if (handsignArrList.size() > 1) {
            randomNum = random.nextInt(handsignArrList.size());
            reselectedChoice = handsignArrList.get(randomNum);
        } else {
            reselectedChoice = handsignArrList.get(0);
        }

        return reselectedChoice;
    }

    public static void recompareChoices(String choiceA, String reselectedChoice) {
        if(reselectedChoice.equals("rock")) {
            if(rockCounter < maxRocks) {
                rockCounter++;
                results();
            } else {
                handsignArrList(reselectedChoice, currentHandsignArrList);
                newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        } else if (newChoiceB.equals("paper")) {
            if(paperCounter < maxPaper) {
                paperCounter++;
                results();
            } else {
                handsignArrList(reselectedChoice, currentHandsignArrList);
                newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        } else if (newChoiceB.equals("scissors")) {
            if(scissorsCounter < maxScissors) {
                scissorsCounter++;
                results();
            } else {
                handsignArrList(reselectedChoice, currentHandsignArrList);
                newChoiceB = reselectChoice(currentHandsignArrList);
                recompareChoices(choiceA, newChoiceB);
            }
        }
    }

    public static void results() {
        String strB = "";
        System.out.println("A's choice: " + choiceA);
        if(newChoiceB.equals("")) {
            System.out.println("B's choice: " + choiceB);
            strB = choiceB;
        } else {
            System.out.println("B's choice: " + newChoiceB);
            strB = newChoiceB;
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

    public static void startMatch(int numOfRounds) {
        for (int i = 1; i <= numOfRounds; i++) {
            choiceB = selectChoice(handsignArr);
            System.out.println("Round " + i);
            if(choiceB.equals("rock")) {
                if(rockCounter < maxRocks) {
                    rockCounter++; 
                    results();
                } else {
                    currentHandsignArrList = handsignArrList(choiceB, handsignArr);
                    newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            } else if(choiceB.equals(choiceA)) {
                if(paperCounter < maxPaper) {
                    paperCounter++;
                    results();
                } else {
                    currentHandsignArrList = handsignArrList(choiceB, handsignArr);
                    newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            } else if (choiceB.equals("scissors")) {
                if(scissorsCounter < maxScissors) {
                    scissorsCounter++;
                    results();
                } else {
                    currentHandsignArrList = handsignArrList(choiceB, handsignArr);
                    newChoiceB = reselectChoice(currentHandsignArrList);
                    recompareChoices(choiceA, newChoiceB);
                }
            }
        }
        System.out.println("Below are the total amount of each choices made by player B.");
        System.out.println("Rock: " + rockCounter);    
        System.out.println("Paper: " + paperCounter);
        System.out.println("Scissors: " + scissorsCounter);
        int total = rockCounter + paperCounter + scissorsCounter;
        System.out.println("rock + paper + scissors = " + total);
    }
    public static void main(String[] args) throws Exception {

        startMatch(numOfRounds);

    }
}
