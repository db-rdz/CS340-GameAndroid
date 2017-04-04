package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Updates when a player gets new destination cards.
 * This updated number also tells other players how many destination cards they have currently.
 *
 * Created by natha on 2/28/2017.
 */

public class UpdatePlayerDestinationCardsCommand implements ICommand {

    //Data members
    private List<DestCard> destCards;

    //Constructor
    public UpdatePlayerDestinationCardsCommand(){}
    public UpdatePlayerDestinationCardsCommand(List<DestCard> destCards) {
        this.destCards = destCards;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.getClientModel().getPlayer_hand().get_destCards().addAll(destCards);
        PlayerActionPresenter._SINGLETON.get_destCards().clear();
        for (DestCard card : destCards) {
            ClientFacade.SINGLETON.getClientModel().getBoardActivity().removeFromSliddingApadter(card);
        }
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerAction();
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerInfo();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }


    public List<DestCard> getDestCards() {
        return destCards;
    }
}