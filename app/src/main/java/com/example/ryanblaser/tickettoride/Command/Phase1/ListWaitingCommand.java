package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ListWaitingCommand implements ICommand { // sent to clients after login/registration
    private List<Integer> list_gameIds;
    public ListWaitingCommand(){}
    public ListWaitingCommand(List<Integer> list){
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
    ClientFacade.SINGLETON.listWaitingGames(list_gameIds); //TODO: Decide which Game class to use
    return null;
    }

    @Override
    public Game getGame() {
    return null;
    }

    public List<Integer> getList_gameIds() {
    return list_gameIds;
    }
}
