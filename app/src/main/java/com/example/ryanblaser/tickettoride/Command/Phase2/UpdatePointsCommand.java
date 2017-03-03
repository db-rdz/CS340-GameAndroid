package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * FROM SERVER -> CLIENT
 * Updates the points of a player by adding to the player's current points
 *
 * Created by natha on 2/27/2017.
 */

public class UpdatePointsCommand implements ICommand {

    //Data members
    private int int_points_to_add;

    //Constructor
    public UpdatePointsCommand(int int_points_to_add) {
        this.int_points_to_add = int_points_to_add;
    }

    //Functions
    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
        return ClientFacade.SINGLETON.updatePoints(int_points_to_add);
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

    public int getInt_points_to_add() {
        return int_points_to_add;
    }
}
