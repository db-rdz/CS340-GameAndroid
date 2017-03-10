package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class InitializeGameCommand implements ICommand {

	@Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
		// TODO Auto-generated method stub
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

}