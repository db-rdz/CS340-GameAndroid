package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * This starts each user with the appropriate cards:
 * Receive 3 destination cards, but pick at least 2
 * Receive 4 random train cards (can include wilds)
 *
 * The ClientModel already takes care of the initial car and point count, so no need to initialize from here.
 *
 * Created by natha on 2/27/2017.
 */

public class InitializeGameCommand implements ICommand {
	private List<TrainCard> hand; //The 4 starting train cards
	private List<DestCard> destinationCards; //The 3 starting destination cards where the player picks at least 1.

	public InitializeGameCommand(List<TrainCard> hand, List<DestCard> dc)
	{
		this.hand = hand;
		destinationCards = dc;
	}

	@Override
	public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        ClientFacade.SINGLETON.getClientModel().getPlayer_hand().initializeHand(hand);
        ClientFacade.SINGLETON.getClientModel().setList_dest_cards(destinationCards);
		ClientFacade.SINGLETON.getClientModel().getMainActivity().switchToGameBoard();
		return null;
	}

	@JsonIgnore
	@Override
	public String getAuthenticationCode() {

		return null;
	}

	@JsonIgnore
	@Override
	public com.example.ryanblaser.tickettoride.Client.User getUser() {

		return null;
	}

	public List<TrainCard> getHand()
	{
		return hand;
	}

	public List<DestCard> getDestinationCards() {
		return destinationCards;
	}

}
