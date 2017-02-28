package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

/**
 * FROM SERVER -> CLIENT
 * Notifies when it's a specific player's turn in the game.
 *
 * Created by natha on 2/28/2017.
 */

public class NotifyTurnCommand implements ICommand {
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
