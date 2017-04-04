package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
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
    private TrainCard trainCard; //TODO: Should be a List? Depends on Client implementation

    //Constructor
    public UpdatePlayerTrainCardsCommand(){}
    public UpdatePlayerTrainCardsCommand(TrainCard trainCard) {
        this.trainCard = trainCard;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.getClientModel().getPlayer_hand().addOneToCardCount(trainCard.getType());
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyCardReceived(trainCard.getType());
        GameBoardPresenter._SINGLETON.refreshBoard();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    public TrainCard getTrainCard() {
        return trainCard;
    }
}