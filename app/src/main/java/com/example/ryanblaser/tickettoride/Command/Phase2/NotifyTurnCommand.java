package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Notifies when it's a specific player's turn in the game.
 *
 * Created by natha on 2/28/2017.
 */

public class NotifyTurnCommand implements ICommand {

    //Data members
    private String str_notify_message;

    //Constructor
    public NotifyTurnCommand() {
        this.str_notify_message = "It's your turn!";
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        PlayerActionPresenter._SINGLETON.refreshFragment();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyTurn();
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



    public String getStr_notify_message() {
        return str_notify_message;
    }
}