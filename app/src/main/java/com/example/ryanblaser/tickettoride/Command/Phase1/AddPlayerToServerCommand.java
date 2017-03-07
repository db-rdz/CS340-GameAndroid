package com.example.ryanblaser.tickettoride.Command.Phase1;

import java.util.List;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class AddPlayerToServerCommand implements ICommand{
    private int i_game_id;
    private String str_username;
    private AddPlayerToServerCommand(){}
    public AddPlayerToServerCommand(String k, int gameId){
        str_username = k;
        i_game_id = gameId;
    }

    @JsonIgnore
    @Override
    public User getUser() {
      return null;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException { // auth key must be changed to Username somewhere
      return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
      return null;}

    @JsonIgnore
    @Override
    public com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game getGame() { return null; }

    public int getI_game_id() {
      return i_game_id;
    }

    public String getStr_username() {
        return str_username;
    }
}