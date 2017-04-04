package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LogoutResponseCommand implements ICommand {

    private LogoutResponseCommand(){}

    @Override
    public List<ICommand> execute(){
        ClientFacade.SINGLETON.logoutSucceeded();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
    return null;
    }

}
