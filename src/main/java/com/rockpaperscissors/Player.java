package main.java.com.rockpaperscissors;

public class Player {
    private String name;
    private int numOfWins;
    private int numOfLoses;
    private int numOfDraws;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfWins() {
        return this.numOfWins;
    }

    public void setNumOfWins(int numOfWins) {
        this.numOfWins = numOfWins;
    }

    public int getNumOfLoses() {
        return this.numOfLoses;
    }

    public void setNumOfLoses(int numOfLoses) {
        this.numOfLoses = numOfLoses;
    }

    public int getNumOfDraws() {
        return this.numOfDraws;
    }

    public void setNumOfDraws(int numOfDraws) {
        this.numOfDraws = numOfDraws;
    }
}
