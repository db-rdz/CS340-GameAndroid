package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.User;

import com.example.ryanblaser.tickettoride.Command.Phase1.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.ListWaitingCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginRegisterResponseCommand.class),
        @JsonSubTypes.Type(value = LogoutResponseCommand.class),
        @JsonSubTypes.Type(value = AddJoinableToClientCommand.class),
        @JsonSubTypes.Type(value = AddWaitingToClientCommand.class),
        @JsonSubTypes.Type(value = AddPlayerToClientCommand.class),
        @JsonSubTypes.Type(value = ListJoinableCommand.class),
        @JsonSubTypes.Type(value = ListWaitingCommand.class),
})
public interface ICommand {

    public List<ICommand> execute() throws GameIsFullException;
    public String getAuthenticationCode();
    public User getUser();
    public Game getGame();
}
