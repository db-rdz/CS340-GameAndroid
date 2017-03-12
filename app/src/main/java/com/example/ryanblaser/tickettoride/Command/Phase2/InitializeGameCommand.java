package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
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

    //Data members
	private List<Player> listOfPlayersInGame;

    //Constructors
    public InitializeGameCommand(){}
    public InitializeGameCommand(List<Player> list) {
        listOfPlayersInGame = list;
    }

    //Functions
	@Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
		ClientFacade.SINGLETON.getClientModel().setListOfPlayersInGame(listOfPlayersInGame);
        ClientFacade.SINGLETON.getClientModel().setCurrent_player(new Player()); //Ensures player is brand new at game start
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

    public List<Player> getListOfPlayersInGame() {
        return listOfPlayersInGame;
    }
}