package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by RyanBlaser on 2/28/17.
 */

public class DestCard implements iDestCard {
    // should map the id of the map used on DestCardTypes
    private int _cardType;

    @JsonProperty("_destination")
    private Pair<String, String> _destination;
    private int points;
    private Boolean _isCompleted = false;

    public DestCard(){}
    public DestCard(String city1, String city2){
        _destination = Pair.of(city1, city2);
    }

    public int get_cardType() {
        return _cardType;
    }

    public Pair<String, String> get_destination() {
        return _destination;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String get_origin() {
        return _destination.getLeft();
    }

    @Override
    public String get_points() {
        return String.valueOf(points);
    }

    @Override
    public String get_routeDestination() {
        return _destination.getRight();
    }

    public Boolean get_isCompleted() {
        return _isCompleted;
    }

    public void set_isCompleted(Boolean _isCompleted) {
        this._isCompleted = _isCompleted;
    }
}
