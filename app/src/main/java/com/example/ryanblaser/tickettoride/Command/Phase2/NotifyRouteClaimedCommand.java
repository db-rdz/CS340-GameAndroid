package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * Sends a message to all the players in the game when a route is claimed by a specific player.
 *
 * Created by natha on 2/28/2017.
 */

public class NotifyRouteClaimedCommand implements ICommand {

    //Data members
    private String str_message;
    private Route route;

    //Constructor
    public NotifyRouteClaimedCommand(){}
    public NotifyRouteClaimedCommand(String str_message, Route r) {
        this.str_message = str_message;
        route = r;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        GameBoardPresenter._SINGLETON.claimRoute(route, str_message);
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyRouteClaimed(str_message);

//        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshGameBoard();
//        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerAction();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }


    public String getStr_message() {
        return str_message;
    }

    public Route getRoute()
    {
        return route;
    }
}
