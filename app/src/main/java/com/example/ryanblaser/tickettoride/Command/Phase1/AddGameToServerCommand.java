package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by RyanBlaser on 2/27/17.
 */

public class AddGameToServerCommand implements ICommand {
    private String str_authentication_code;

    private AddGameToServerCommand() {
    }

    public AddGameToServerCommand(String k) {
        str_authentication_code = k;
    }

    @JsonProperty("str_authentication_code")
    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }

    @Override
    public List<ICommand> execute() {
        return null;

    }
}