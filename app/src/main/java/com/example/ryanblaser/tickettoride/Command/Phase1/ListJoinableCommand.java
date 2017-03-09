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

    /**
     * This is a list of gameIds the Client will receive
     * We don't use Game because the client shouldn't have access to the games they're apart of.
     */
    private List<Integer> list_gameIds;
    public ListJoinableCommand(){}
    public ListJoinableCommand(List<Integer> list){
        list_gameIds = list;}

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
        ClientFacade.SINGLETON.listJoinableGames(list_gameIds);

        return null;
    }


    public List<Integer> getList_gameIds() {
        return list_gameIds;
    }
}
