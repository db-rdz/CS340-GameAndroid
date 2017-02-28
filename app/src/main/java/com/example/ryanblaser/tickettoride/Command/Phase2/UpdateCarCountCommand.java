package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

/**
 * This class updates the car amount for a player.
 * Decreases the car count whenever a ClaimRouteCommand is called
 *
 * Created by natha on 2/27/2017.
 */

public class UpdateCarCountCommand implements ICommand {
    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }
}
