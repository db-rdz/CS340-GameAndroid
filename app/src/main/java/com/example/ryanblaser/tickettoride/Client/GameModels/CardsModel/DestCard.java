package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

import android.util.Pair;

/**
 * Created by RyanBlaser on 2/28/17.
 */

public class DestCard implements iDestCard {
    private int _cardType;
    private Pair<String, String> _destination;
    private int points;

    public DestCard(String c1, String c2){
        _destination = new Pair<>(c1, c2);
    }
}
