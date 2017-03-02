package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.Phase1.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * FROM SERVER -> CLIENT
 * Updates when a player gets new destination cards.
 * This updated number also tells other players how many destination cards they have currently.
 *
 * Created by natha on 2/28/2017.
 */

public class UpdatePlayerDestinationCardsCommand implements ICommand {

    //Data members
    private DestCard destCard;

    //Constructor
    public UpdatePlayerDestinationCardsCommand(DestCard destCard) {
        this.destCard = destCard;
    }

    //Functions
    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
        return ClientFacade.SINGLETON.updatePlayerDestinationCards(); //TODO: Need an argument for the client
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

    public DestCard getDestCard() {
        return destCard;
    }
}
