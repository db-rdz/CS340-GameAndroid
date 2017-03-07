package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;

public class ListJoinableCommand implements ICommand { // sent to clients after login/registration

    private List<Game> list_game_list;
    public ListJoinableCommand(){}
    public ListJoinableCommand(List<Game> list){
    list_game_list = list;}

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

    @Override
    public List<ICommand> execute(){
        ClientFacade.SINGLETON.listJoinableGames(list_game_list);

        return null;
    }

    @JsonIgnore
    @Override
    public Game getGame() {
    return null;
    }

    @JsonProperty("list_game_list")
    public List<Game> getList_game_list() {
    return list_game_list;
    }
}
