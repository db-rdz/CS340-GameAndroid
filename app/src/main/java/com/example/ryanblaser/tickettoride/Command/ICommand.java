package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.User;

import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
        @JsonSubTypes.Type(value = ListResumableCommand.class),
})
public interface ICommand {
    public CommandContainer execute() throws GameIsFullException;
    public String getAuthenticationCode();
    public User getUser();
    public Game getGame();
}
