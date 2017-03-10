package com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel;

import android.graphics.Color;

/**
 * Created by benjamin on 6/03/17.
 */

public class Player {

    public Player(String name, int trainCards, int detinationCards, int color, int points){
        _userName = name;
        _noOfDestCards = detinationCards;
        _noOfTrainCards = trainCards;
        _playerColor = color;
        _points = points;
    }

    public Player(){

    }

    private PlayerCardHand _Hand = new PlayerCardHand();
    private String _userName = null;
    private int _noOfTrainCards = 0;
    private int _noOfDestCards = 0;
    private int _playerColor = Color.BLACK;
    private int _points = 0;


    //-------------------------------------GETTERS AND SETTERS------------------------------------//

    public PlayerCardHand get_Hand() { return _Hand; }
    public void set_Hand(PlayerCardHand _Hand) { this._Hand = _Hand; }

    public String get_userName() { return _userName; }
    public void set_userName(String _userName) { this._userName = _userName; }

    public int get_noOfTrainCards() { return _noOfTrainCards; }
    public void set_noOfTrainCards(int _noOfTrainCards) { this._noOfTrainCards = _noOfTrainCards; }

    public int get_noOfDestCards() { return _noOfDestCards; }
    public void set_noOfDestCards(int _noOfDestCards) { this._noOfDestCards = _noOfDestCards; }

    public int get_playerColor() { return _playerColor; }
    public void set_playerColor(int _playerColor) { this._playerColor = _playerColor; }

    public int get_points() { return _points; }
    public void set_points(int _points) { this._points = _points; }

}
