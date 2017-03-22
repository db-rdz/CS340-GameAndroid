package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This command is called after a player's action. (Will decide later specifically to broadcast)
 * At the very least, when a route is claimed this command will be called.
 * This tells everyone in the game what a player has done during their turn.
 *
 * Created by natha on 2/28/2017.
 */

public class BroadcastToChatCommand implements ICommand {
    //Data members
    // Ryan: added authenticationCode so that I can display what user sent the message in the chatroom
    String message;
    String str_authenticationCode;
    int gameId;

    //Constructors
    public BroadcastToChatCommand(){}
    public BroadcastToChatCommand(int g, String code, String messageToSend) {
        str_authenticationCode = code;
        message = messageToSend;
        gameId = g;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        //TODO: connect this to the Messages part in the GUI
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return str_authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public String getMessage() {
        return message;
    }

    public int getGameId()
    {
        return gameId;
    }

    public String get_strAuthenticationCode()
    {
        return str_authenticationCode;
    }
}
