package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

/**
 * This command is called after a player's action. (Will decide later specifically to broadcast)
 * At the very least, when a route is claimed this command will be called.
 * This tells everyone in the game what a player has done during their turn.
 *
 * Created by natha on 2/28/2017.
 */

public class BroadcastToChatCommand implements ICommand {
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
