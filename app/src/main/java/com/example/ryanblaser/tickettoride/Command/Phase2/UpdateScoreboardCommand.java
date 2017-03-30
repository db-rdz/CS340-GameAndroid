package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.Scoreboard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Updates the points of a player by adding to the player's current points
 *
 * Created by natha on 2/27/2017.
 */

public class UpdateScoreboardCommand implements ICommand {

    //Data members
    private List<Scoreboard> scoreboards;

    //Constructor
    public UpdateScoreboardCommand(){}
    public UpdateScoreboardCommand(List<Scoreboard> s) {
        scoreboards = s;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.getClientModel().setScoreboards(scoreboards);
        PlayerInfoPresenter._SINGLETON.refreshPlayerInfo();
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


    public List<Scoreboard> getScoreboards() {
        return scoreboards;
    }

}
