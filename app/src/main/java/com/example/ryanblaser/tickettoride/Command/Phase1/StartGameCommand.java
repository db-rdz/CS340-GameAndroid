package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class StartGameCommand implements ICommand {
    private int gameId;
    private String str_authentication_code;
    private StartGameCommand(){}
    public StartGameCommand(int g, String k){
        gameId = g;
        str_authentication_code = k;}

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
        return str_authentication_code;}

    @JsonIgnore
    @Override
    public Game getGame() {
        return null;
    }
}
