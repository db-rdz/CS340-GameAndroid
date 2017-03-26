package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;

import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToJoinableListCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.SwitchToWaitingActivityCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.InitializeGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.NotifyTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.ShowMessageCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdateCarCountCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdateFaceUpTableTrainCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdatePlayerDestinationCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdatePlayerTrainCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdateScoreboardCommand;
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
        @JsonSubTypes.Type(value = SwitchToWaitingActivityCommand.class),
        @JsonSubTypes.Type(value = AddGameToJoinableListCommand.class),
        @JsonSubTypes.Type(value = AddPlayerToClientCommand.class),
        @JsonSubTypes.Type(value = ListJoinableCommand.class),
        @JsonSubTypes.Type(value = InitializeGameCommand.class),
        @JsonSubTypes.Type(value = ShowMessageCommand.class),
        @JsonSubTypes.Type(value = NotifyTurnCommand.class),
        @JsonSubTypes.Type(value = UpdateCarCountCommand.class),
        @JsonSubTypes.Type(value = UpdateFaceUpTableTrainCardsCommand.class),
        @JsonSubTypes.Type(value = UpdatePlayerTrainCardsCommand.class),
        @JsonSubTypes.Type(value = UpdatePlayerDestinationCardsCommand.class),
        @JsonSubTypes.Type(value = UpdateScoreboardCommand.class),
        @JsonSubTypes.Type(value = DeleteGameCommand.class)
})
public interface ICommand {


    public List<ICommand> execute() throws GameIsFullException, IClient.UserAlreadyLoggedIn;
    public String getAuthenticationCode();
    public User getUser();
}
