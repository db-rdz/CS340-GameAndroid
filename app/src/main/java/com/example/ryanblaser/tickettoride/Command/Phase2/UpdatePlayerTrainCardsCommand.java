package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * FROM SERVER -> CLIENT
 * Adds the selected train card to the player's hand. So the player should have a visual que of the new card on his screen.
 * This command is called whenever a GetCardCommand is called.
 *
 * Created by natha on 2/27/2017.
 */

public class UpdatePlayerTrainCardsCommand implements ICommand {

    //Data members
    private TrainCard trainCard; //TODO: Should be a List? Depends on Client implementation

    //Constructor
    public UpdatePlayerTrainCardsCommand(TrainCard trainCard) {
        this.trainCard = trainCard;
    }

    //Functions
    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
        return ClientFacade.SINGLETON.updatePlayerTrainCards(); //TODO: Need an argument for the ClientFacade
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
