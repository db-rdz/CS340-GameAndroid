package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddGameToServerCommand implements ICommand {
    private Game game;
    private String str_authentication_code;

    private AddGameToServerCommand() {
    }

    public AddGameToServerCommand(Game g, String k) {
        game = g;
        str_authentication_code = k;
    }

    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }

    @Override
    public User getUser() {
        return null;
    }
    //
    @Override
    public CommandContainer execute() {
//    ServerFacade.SINGLETON.addGame(game);
        return null; //TODO: Stub
    }
//  public int getAuthenticationCode(){
//    return str_authentication_code;}}
}