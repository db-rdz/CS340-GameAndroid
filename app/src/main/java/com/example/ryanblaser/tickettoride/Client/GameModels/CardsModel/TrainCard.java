package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanBlaser on 2/28/17.
 */


public class TrainCard implements iTrainCard {
    //------------------------------------STATIC VARIABLES------------------------------------//
    List<TrainCard> _L_Deck = new ArrayList<>();
    private String type;

    public TrainCard(){}
    public TrainCard(String typeStr) {
        type = typeStr;
    }

    @Override
    public void shuffle() {

    }

    public List<TrainCard> get_L_Deck() {
        return _L_Deck;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
