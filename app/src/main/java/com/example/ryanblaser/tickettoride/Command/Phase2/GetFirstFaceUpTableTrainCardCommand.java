package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.Phase1.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * FROM CLIENT -> SERVER
 * This class will allow one player to grab one train card from the face up train cards on the table.
 * There's another command to grab a second card either from the deck or face up train cards. IF the player
 * doesn't choose a wild card first.
 *
 * Created by natha on 2/27/2017.
 */

public class GetFirstFaceUpTableTrainCardCommand implements ICommand {
    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
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

    @JsonIgnore
    @Override
    public Game getGame() {
        return null;
    }
}
