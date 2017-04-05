package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.State;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by natha on 4/4/2017.
 */

public class NotifyDestCardCommand implements ICommand {

    public NotifyDestCardCommand(){}
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyPickNewDestCards();
        if (ClientFacade.SINGLETON.getClientModel().getState().equals(State.LAST_TURN)) {
            ClientFacade.SINGLETON.getClientModel().setState(State.LAST_TURN_PICKING_DEST_CARD);
        }
        else {
            ClientFacade.SINGLETON.getClientModel().setState(State.PICKING_DEST_CARD);
        }
        //Refresh all views
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshGameBoard();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshChat();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerAction();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerInfo();        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }
}
