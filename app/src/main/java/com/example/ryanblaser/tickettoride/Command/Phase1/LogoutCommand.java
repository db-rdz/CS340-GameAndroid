package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LogoutCommand implements ICommand {
    private User user;
    private LogoutCommand(){}
    public LogoutCommand(User user){
        this.user = user;
    }



    @Override
    public List<ICommand> execute(){
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return user;
    }
}
