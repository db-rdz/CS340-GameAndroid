package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

public class EndTurnCommand implements ICommand {

	public EndTurnCommand(){}

	@Override
	public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
		ClientFacade.SINGLETON.getClientModel().setState(ClientModel.State.NOT_YOUR_TURN);
		return null;
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

}