package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

/**
 * Created by RyanBlaser on 2/28/17.
 */


public class TrainCard implements iTrainCard {

    public TrainCard(String t){
        type = t;
    }

    private String type;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
