package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class NotifyDestCardCompletedCommand implements ICommand {

	private String message;

	public NotifyDestCardCompletedCommand(){}

	@Override
	public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
		ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyDestCardCompleted(message);

//		ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshPlayerInfo();
		return null;
	}

	@JsonIgnore
	@Override
	public String getAuthenticationCode() {
		return null;
	}

	public String getMessage() {
		return message;
	}
}
