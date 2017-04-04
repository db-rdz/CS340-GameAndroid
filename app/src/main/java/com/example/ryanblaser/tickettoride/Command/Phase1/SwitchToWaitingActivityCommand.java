package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * This command is only received from ALREADY logged in users on the server
 */
public class SwitchToWaitingActivityCommand implements ICommand { // sent after changes from what List... commands sent

    private int gameId;
    private List<String> usernames = new ArrayList<>(); //Server sends back a list of usernames to add to the clientmodel
    private Boolean isCreator;

    public SwitchToWaitingActivityCommand() {
    }

    public SwitchToWaitingActivityCommand(int id, List<String> names, Boolean creator) {
        gameId = id;
        usernames = names;
        isCreator = creator;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public List<ICommand> execute() {

        for (int i = 0; i < usernames.size(); i++) {
            ClientFacade.SINGLETON.getClientModel().addPlayerToGame(usernames.get(i), gameId);
        }
        ClientFacade.SINGLETON.getClientModel().setBoolean_is_creator_of_game(isCreator);
        ClientFacade.SINGLETON.getClientModel().setInt_curr_gameId(gameId);

        LobbyPresenter.SINGLETON.switchToWaitingView();
        return null;
    }

    public int getGameId() {
        return gameId;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public Boolean getIsCreator() {
        return isCreator;
    }

}