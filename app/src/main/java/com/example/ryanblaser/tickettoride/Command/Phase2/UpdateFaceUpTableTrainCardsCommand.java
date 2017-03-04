package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * When a face up table train card is picked from a player, the game will get updated
 * to replace that card with a new one.
 *
 * Created by natha on 2/28/2017.
 */

public class UpdateFaceUpTableTrainCardsCommand implements ICommand {

    //Data members
    private TrainCard trainCard; //TODO: make into a list?

    //Constructor
    public UpdateFaceUpTableTrainCardsCommand(TrainCard trainCard) {
        this.trainCard = trainCard;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.updateFaceUpTableTrainCards(); //TODO: Need arguments?
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

    @JsonIgnore
    @Override
    public Game getGame() {
        return null;
    }

    public TrainCard getTrainCard() {
        return trainCard;
    }
}