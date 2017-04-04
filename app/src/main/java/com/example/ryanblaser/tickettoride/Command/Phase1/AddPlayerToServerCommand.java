package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class AddPlayerToServerCommand implements ICommand {
    private int i_game_id;
    private String str_authentication_code;
    private AddPlayerToServerCommand(){}
    public AddPlayerToServerCommand(String k, int gameId){
        str_authentication_code = k;
        i_game_id = gameId;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException { // auth key must be changed to Username somewhere

      return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
      return null;}


    public int getI_game_id() {
      return i_game_id;
    }

    public String getStr_authentication_code() {
        return str_authentication_code;
    }
}