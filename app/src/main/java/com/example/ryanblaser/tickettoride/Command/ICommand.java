package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;

import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToJoinableListCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToServerCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToServerCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LogoutCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.RegisterCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.StartGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.SwitchToWaitingActivityCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.BroadcastToChatCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.ClaimRouteCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.EndTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.FirstTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetDestinationCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetFaceUpTableTrainCardCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetTopDeckTrainCardCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.InitializeGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.KeepAllDestCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.NotifyRouteClaimedCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.NotifyTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.RejectDestinationCardCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.ShowMessageCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.UpdateCarCountAndHandCommand;
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
@JsonSubTypes(value = {
        // Phase1
        @JsonSubTypes.Type(value = AddGameToJoinableListCommand.class),
        @JsonSubTypes.Type(value = AddGameToServerCommand.class),
        @JsonSubTypes.Type(value = AddPlayerToClientCommand.class),
        @JsonSubTypes.Type(value = AddPlayerToServerCommand.class),
        @JsonSubTypes.Type(value = DeleteGameCommand.class),
        @JsonSubTypes.Type(value = GetCommandsCommand.class),
        @JsonSubTypes.Type(value = ListJoinableCommand.class),
        @JsonSubTypes.Type(value = LoginCommand.class),
        @JsonSubTypes.Type(value = LoginRegisterResponseCommand.class),
        @JsonSubTypes.Type(value = LogoutCommand.class),
        @JsonSubTypes.Type(value = RegisterCommand.class),
        @JsonSubTypes.Type(value = StartGameCommand.class),
        @JsonSubTypes.Type(value = SwitchToWaitingActivityCommand.class),
        // Phase 2/3
        @JsonSubTypes.Type(value = BroadcastToChatCommand.class),
        @JsonSubTypes.Type(value = ClaimRouteCommand.class),
        @JsonSubTypes.Type(value = EndTurnCommand.class),
        @JsonSubTypes.Type(value = FirstTurnCommand.class),
        @JsonSubTypes.Type(value = GetDestinationCardsCommand.class),
        @JsonSubTypes.Type(value = GetFaceUpTableTrainCardCommand.class),
        @JsonSubTypes.Type(value = GetTopDeckTrainCardCommand.class),
        @JsonSubTypes.Type(value = InitializeGameCommand.class),
        @JsonSubTypes.Type(value = KeepAllDestCardsCommand.class),
        @JsonSubTypes.Type(value = NotifyRouteClaimedCommand.class),
        @JsonSubTypes.Type(value = NotifyTurnCommand.class),
        @JsonSubTypes.Type(value = RejectDestinationCardCommand.class),
        @JsonSubTypes.Type(value = ShowMessageCommand.class),
        @JsonSubTypes.Type(value = UpdateCarCountAndHandCommand.class),
        @JsonSubTypes.Type(value = UpdateFaceUpTableTrainCardsCommand.class),
        @JsonSubTypes.Type(value = UpdatePlayerDestinationCardsCommand.class),
        @JsonSubTypes.Type(value = UpdatePlayerTrainCardsCommand.class),
        @JsonSubTypes.Type(value = UpdateScoreboardCommand.class)

})
public interface ICommand {


    public List<ICommand> execute() throws GameIsFullException, IClient.UserAlreadyLoggedIn;
    public String getAuthenticationCode();
    public User getUser();
}
