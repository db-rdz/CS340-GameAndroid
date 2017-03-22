package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class AddPlayerToClientCommand implements ICommand { // sent after changes from what List... commands sent
    private int int_game_id;
    private String str_username;
    public AddPlayerToClientCommand(){}
    public AddPlayerToClientCommand(String name, int gameId){
    str_username = name;
    int_game_id = gameId;}

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    public String getUsername()
    {
      return str_username;
    }

    @JsonIgnore
    @Override
        public User getUser() {
        return null;
    }

    @Override
    public List<ICommand> execute() throws GameIsFullException {
        ClientFacade.SINGLETON.getClientModel().addPlayerToGame(str_username, int_game_id);
        ClientFacade.SINGLETON.getClientModel().getGameActivity().refreshList();
        return null;
    }


    public int getInt_game_id() {
    return int_game_id;
    }

    public String getStr_username() {
    return str_username;
    }
}
