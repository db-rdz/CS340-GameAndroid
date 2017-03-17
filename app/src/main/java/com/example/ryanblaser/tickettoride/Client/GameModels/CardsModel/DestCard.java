package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by RyanBlaser on 2/28/17.
 */

public class DestCard implements iDestCard {
    // should map the id of the map used on DestCardTypes
    private int _cardType;
    private Pair<String, String> _destination;
    private String points;

    public DestCard(String city1, String city2){

        _destination = Pair.of(city1, city2);
    }



    @Override
    public String get_destination() {
        return null;
    }

    @Override
    public String get_origin() {
        return null;
    }

    @Override
    public String get_points() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
