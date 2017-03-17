package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;

import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToJoinableListCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddJoinableGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginRegisterResponseCommand.class),
        @JsonSubTypes.Type(value = LogoutResponseCommand.class),
        @JsonSubTypes.Type(value = AddJoinableGameCommand.class),
        @JsonSubTypes.Type(value = AddGameToJoinableListCommand.class),
        @JsonSubTypes.Type(value = AddPlayerToClientCommand.class),
        @JsonSubTypes.Type(value = ListJoinableCommand.class),
        @JsonSubTypes.Type(value = DeleteGameCommand.class)
})
public interface ICommand {


    public List<ICommand> execute() throws GameIsFullException, IClient.UserAlreadyLoggedIn;
    public String getAuthenticationCode();
    public User getUser();
}
