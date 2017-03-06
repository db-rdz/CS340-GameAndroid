package com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel;

import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;

/**
 * Created by RyanBlaser on 2/28/17.
 */

public class DestCard implements iDestCard {

    public DestCard(City c1, City c2){
        _destination = new Pair<>(c1, c2);
    }

    private Pair<City, City> _destination;
}
