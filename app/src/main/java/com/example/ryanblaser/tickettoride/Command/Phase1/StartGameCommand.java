package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class StartGameCommand implements ICommand {
    private int gameId;
    private List<String> usernamesInGame;
    private String authenticationCode;
    private StartGameCommand(){}
    public StartGameCommand(int g, List<String> k, String code){
        gameId = g;
        usernamesInGame = k;
        authenticationCode = code;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<ICommand> execute(){
        return null;
    }

    @Override
    public String getAuthenticationCode(){
        return authenticationCode;}

    public int getGameId() {
        return gameId;
    }

    public List<String> getUsernamesInGame() {
        return usernamesInGame;
    }


}
