package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Override
    public List<ICommand> execute(){
        ClientFacade.SINGLETON.listJoinableGames(list_gameIds);
        ClientFacade.SINGLETON.getClientModel().getMainActivity().refreshLobbyView();
        return null;
    }


    public List<Integer> getList_gameIds() {
        return list_gameIds;
    }
}
