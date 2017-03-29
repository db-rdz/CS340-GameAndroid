package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * When a face up table train card is picked from a player, the game will get updated
 * to replace that card with a new one.
 *
 * We receive a list of 5 random train cards at the start of the game for the table. When a card
 * is taken, we replace it with another one from the server. This list will be stored within the
 * PlayerActionPresenter instead of the client model.
 *
 * The List of TrainCards will come from the server and this command will simply update the
 * face up train cards
 *
 * Created by natha on 2/28/2017.
 */

public class UpdateFaceUpTableTrainCardsCommand implements ICommand {

    //Data members
    private List<TrainCard> trainCards;

    public UpdateFaceUpTableTrainCardsCommand(){}
    //Constructor
    public UpdateFaceUpTableTrainCardsCommand(List<TrainCard> list) {
        trainCards = list;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        PlayerActionPresenter._SINGLETON.set_faceUpTrainCards(trainCards);
        //Copy The ChatPresenter chatFragment = new ChatFragment()
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }


    public List<TrainCard> getTrainCards() {
        return trainCards;
    }
}
