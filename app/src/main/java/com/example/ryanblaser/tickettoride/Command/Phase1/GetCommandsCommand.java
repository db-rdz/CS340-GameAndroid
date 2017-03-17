package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GetCommandsCommand implements ICommand {
    private String username;
    private int lastCommandRecievedIndex;
    public GetCommandsCommand(){}
    public GetCommandsCommand(String username, int index){
      this.username = username;
        lastCommandRecievedIndex = index;}

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
      return null;
    }



    public String getUsername()
    {
      return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLastCommandRecievedIndex() {
        return lastCommandRecievedIndex;
    }

}