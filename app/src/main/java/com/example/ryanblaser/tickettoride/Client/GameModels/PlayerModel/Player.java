package com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel;

import android.graphics.Color;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;

import java.util.List;

/**
 * This class is simply a data storage class for a user's game data.
 * The User class will have a Player variable (this class) to store all the data.
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

    private String _userName = null;
    private int _noOfTrainCards = 4; //Each player starts with 4
    private int _noOfDestCards = 3; //Each player starts with 3, then just subtracts it as he rejects them
    private int _playerColor = Color.MAGENTA;
    private int _car_count = 45; //Each player always starts the game with 45 train cars
    private int _points = 0;

    //--------------------------------------CLASS FUNCTIONS---------------------------------------//

    /**
     * Nathan
     * Updates the car count by subtracting the current number of cars by the amount of cars used.
     * @param numOfCarsUsed
     */
    public void updateCarCount(int numOfCarsUsed) {
        int holder = _car_count;
        if ((holder -= numOfCarsUsed) >= 0) {
            _car_count -= numOfCarsUsed;
        }
    }

    /**
     * Nathan
     * Updates the player's points by adding the gained point total to the current amount of points
     * @param pointsToAdd
     */
    public void updatePoints(int pointsToAdd) {
        _points += pointsToAdd;
    }

    /**
     * Nathan
     * Updates the number of train cards by adding the gained amount of cards to the current total
     * @param cardAmountToAdd
     */
    public void updateCurrentTrainCards(int cardAmountToAdd) { _noOfTrainCards += cardAmountToAdd; }

    /**
     * Nathan
     * Updates the number of destination cards by adding the gained amount of cards to the current total
     * @param amountOfRejectedCards
     */
    public void updateCurrentDestinationCards(int amountOfRejectedCards) {
        _noOfDestCards -= amountOfRejectedCards;
    }

    //-------------------------------------GETTERS AND SETTERS------------------------------------//

//    public PlayerCardHand get_Hand() { return _Hand; }
//    public void set_Hand(PlayerCardHand _Hand) { this._Hand = _Hand; }

    public String get_userName() { return _userName; }
    public void set_userName(String _userName) { this._userName = _userName; }

    public int get_noOfTrainCards() { return _noOfTrainCards; }
    public void set_noOfTrainCards(int _noOfTrainCards) { this._noOfTrainCards = _noOfTrainCards; }

    public int get_noOfDestCards() { return _noOfDestCards; }
    public void set_noOfDestCards(int _noOfDestCards) { this._noOfDestCards = _noOfDestCards; }

    public int get_playerColor() { return _playerColor; }
    public void set_playerColor(int _playerColor) { this._playerColor = _playerColor; }

    public int get_car_count() {
        return _car_count;
    }
    public void set_car_count(int _car_count) {
        this._car_count = _car_count;
    }

    public int get_points() { return _points; }
    public void set_points(int _points) { this._points = _points; }

}
