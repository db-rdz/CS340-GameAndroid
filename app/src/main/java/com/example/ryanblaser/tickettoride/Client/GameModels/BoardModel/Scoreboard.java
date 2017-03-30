package com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel;

import android.graphics.Color;

/**
 * Used for the PlayerInfoFragment
 * Created by rblaser on 3/23/2017.
 */

public class Scoreboard {
    private int points;
    private int numberOfTrainCards;
    private int numberOfDestCards;
    private String playerColor;
    private int playerCarCount;

    public Scoreboard() {}

    public int getPoints() { return points; }
    public void addPoints(int addedPoints)
    {
        points += addedPoints;
    }

    public int getNumberOfTrainCards() { return numberOfTrainCards; }
    public void addTrainCards(int addedCard)
    {
        numberOfTrainCards += addedCard;
    }

    public int getNumberOfDestCards() { return numberOfDestCards; }
    public void addDestCards(int addedCard)
    {
        numberOfDestCards += addedCard;
    }

    public int get_stringToAndroidColor() {
        switch (playerColor) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.CYAN;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.MAGENTA;
        }
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public int getPlayerCarCount() {
        return playerCarCount;
    }
}