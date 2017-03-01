package com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel;

/**
 * Created by benjamin on 28/02/17.
 */

public class testDestinationCard implements iDestCard {

    public testDestinationCard(String a, String b, String c){
        _ori = a;
        _des = b;
        _points = c;
    }

    private String _des;
    private String _ori;
    private String _points;


    @Override
    public String get_destination() {
        return _des;
    }

    @Override
    public String get_origin() {
        return _ori;
    }

    @Override
    public String get_points() {
        return _points;
    }
}
