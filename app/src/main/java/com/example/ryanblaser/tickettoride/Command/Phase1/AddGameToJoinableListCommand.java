package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * This command is only received by NEWLY logged in users
 * Created by natha on 3/16/2017.
 */

public class AddGameToJoinableListCommand implements ICommand {

    private int gameId;
    public AddGameToJoinableListCommand(){}
    public AddGameToJoinableListCommand(int id){
        gameId = id;}

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

    @Override
    public List<ICommand> execute() {
        ClientFacade.SINGLETON.getClientModel().addJoinableGame(gameId);
        ClientFacade.SINGLETON.getClientModel().getMainActivity().refreshLobbyView();
        return null;
    }

    public int getGameId() {
        return gameId;
    }

}
