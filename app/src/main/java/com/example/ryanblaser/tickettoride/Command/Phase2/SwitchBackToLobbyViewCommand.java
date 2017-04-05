package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * Created by natha on 4/4/2017.
 */

public class SwitchBackToLobbyViewCommand implements ICommand {

    public SwitchBackToLobbyViewCommand(){}

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifySwitchBackToLobby();
        LobbyPresenter.SINGLETON.refreshGameLobby();
        ClientFacade.SINGLETON.getClientModel().getEndGameActivity().switchBackToLobbyView();
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }
}
