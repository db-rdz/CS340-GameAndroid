package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * ONLY IN CLIENT MODEL
 * This class updates the car amount for a player.
 * Decreases the car count whenever a ClaimRouteCommand is called.
 * So this class will contain the amount of cars USED to claim a route.
 * The ClientFacade will then subtract the current player's cars by the amount given in this class.
 *
 * Created by natha on 2/27/2017.
 */

public class UpdateCarCountAndHandCommand implements ICommand {

    //Data members
    private int int_cars_used;
    private List<TrainCard> cardsUsedToClaimRoute;

    //Constructor
    public UpdateCarCountAndHandCommand(){}
    public UpdateCarCountAndHandCommand(int int_used_cars) {
        this.int_cars_used = int_used_cars;
    }
    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.updateCarCount(int_cars_used);
        ClientFacade.SINGLETON.removeCardsUsed(cardsUsedToClaimRoute);
        if (ClientFacade.SINGLETON.getClientModel().getCurrent_player().get_car_count() < 3) {
            ClientFacade.SINGLETON.initiateLastTurn();
        }
        return null;
    }

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

    public int getInt_cars_used() {
        return int_cars_used;
    }

    public List<TrainCard> getCardsUsedToClaimRoute()
    {
        return cardsUsedToClaimRoute;
    }
}
