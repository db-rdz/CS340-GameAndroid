package com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 8/03/17.
 */

public class ActionBoard {

    public static ActionBoard _SINGLETON = new ActionBoard();

    private List<DestCard> _initialDesCards = new ArrayList<>();
    private List<TrainCard> _fourTrainCards = new ArrayList<>();
    private TrainCard _topOfTrainCardDeck;

    //------------------------------------SETTERS AND GETTERS-------------------------------------//
    public TrainCard get_topOfTrainCardDeck() { return _topOfTrainCardDeck; }
    public void set_topOfTrainCardDeck(TrainCard _topOfTrainCardDeck) { this._topOfTrainCardDeck = _topOfTrainCardDeck; }

    public List<TrainCard> get_fourTrainCards() { return _fourTrainCards; }
    public void set_fourTrainCards(List<TrainCard> _fourTrainCards) { this._fourTrainCards = _fourTrainCards; }

    public List<DestCard> get_initialDesCards() { return _initialDesCards; }
    public void set_initialDesCards(List<DestCard> _initialDesCards) { this._initialDesCards = _initialDesCards;}

    public void resetActionBoardModel(){
        _SINGLETON = new ActionBoard();
    }
}
