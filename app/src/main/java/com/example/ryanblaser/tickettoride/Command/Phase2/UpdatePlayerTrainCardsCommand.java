package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Adds the selected train card to the player's hand. So the player should have a visual que of the new card on his screen.
 * This command is called whenever a GetCardCommand is called.
 *
 * Created by natha on 2/27/2017.
 */

public class UpdatePlayerTrainCardsCommand implements ICommand {

    //Data members
    private int addNewCardAmount;

    //Constructor
    public UpdatePlayerTrainCardsCommand(int amount) {
        addNewCardAmount = amount;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.updatePlayerTrainCards(addNewCardAmount);
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


    public int getAddNewCardAmount() {
        return addNewCardAmount;
    }
}
