package main.java.com.rockpaperscissors;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<String> handsignOptions = new ArrayList<>();
    private int numOfWins;
    private int numOfLoses;
    private int numOfDraws;

    // public Player(String name) {
    // this.name = name;
    // }

    public Player(String name, String[] handsignOptions) {
        this.name = name;
        for(int i = 0; i < handsignOptions.length; i++) {
            this.handsignOptions.add(handsignOptions[i]);
        }
    }

    public ArrayList<String> getHandsignOptions() {
        return handsignOptions;
    }

    public String getName() {
        return name;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void increaseNumOfWins() {
        this.numOfWins++;
    }

    public int getNumOfLoses() {
        return numOfLoses;
    }

    public void increaseNumOfLoses() {
        this.numOfLoses++;
    }

    public int getNumOfDraws() {
        return numOfDraws;
    }

    public void increaseNumOfDraws() {
        this.numOfDraws++;
    }
}
