package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by RyanBlaser on 3/29/17.
 */

public class UpdateServerDestCardsCommand implements ICommand {

    private List<DestCard> destCards;

    public UpdateServerDestCardsCommand(){}
    public UpdateServerDestCardsCommand(List<DestCard> cards) {
        destCards = cards;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        ClientFacade.SINGLETON.getClientModel().setDestCardsFromServer(destCards);
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyPickNewDestCards();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerAction();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DestCard> getDestCards() {
        return destCards;
    }

}