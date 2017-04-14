package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LogoutCommand implements ICommand {
    private String authenticationCode;

    private LogoutCommand(){}
    public LogoutCommand(String authenticationCode){
        this.authenticationCode = authenticationCode;
    }

    @Override
    public List<ICommand> execute(){
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

}
