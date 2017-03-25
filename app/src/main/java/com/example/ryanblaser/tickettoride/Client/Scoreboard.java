package com.example.ryanblaser.tickettoride.Client;

/**
 * Used for the PlayerInfoFragment
 * Created by rblaser on 3/23/2017.
 */

public class Scoreboard {
    private int points;
    private int numberOfTrainCards;
    private int numberOfDestCards;

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

}