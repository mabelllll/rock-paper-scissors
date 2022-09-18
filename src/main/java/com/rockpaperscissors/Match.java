package main.java.com.rockpaperscissors;
import java.util.ArrayList;

public class Match {
    private int numberOfMatches;
    private int maxRockChoice;
    private int maxPaperChoice;
    private int maxScissorsChoice;
    private ArrayList<String> handSignArr = new ArrayList<>();

    public ArrayList<String> getHandsignArr() {
        return this.handSignArr;
    }

    public void setHandsignArr(String[] handSignArr) {
        for(int i = 0; i < handSignArr.length; i++) {
            this.handSignArr.add(handSignArr[i]);
        }
    }

    public int getNumOfMatches() {
        return this.numberOfMatches;
    }

    public void setNumOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getMaxRockChoice() {
        return maxRockChoice;
    }

    public void setMaxRockChoice(int maxRockChoice) {
        this.maxRockChoice = maxRockChoice;
    }

    public int getMaxPaperChoice() {
        return maxPaperChoice;
    }

    public void setMaxPaperChoice(int maxPaperChoice) {
        this.maxPaperChoice = maxPaperChoice;
    }

    public int getMaxScissorsChoice() {
        return maxScissorsChoice;
    }

    public void setMaxScissorsChoice(int maxScissorsChoice) {
        this.maxScissorsChoice = maxScissorsChoice;
    }
}
