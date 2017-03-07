package com.example.ryanblaser.tickettoride.Command.Phase1;

import java.util.ArrayList;
import java.util.List;

import com.example.ryanblaser.tickettoride.Client.GameModels.Game;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * @author Nathan
 * This class will delete all the commands that the GetCommandsCommand class received and sent to the client.
 *
 */

public class DeleteGottenCommands implements ICommand{

	//Data members
	private String username;
	
	//Constructor
	public DeleteGottenCommands() {}
	public DeleteGottenCommands(String username) {
		this.username = username;
	}
	
	//Functions
	@Override
	public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {

		return null; //Should be null anyway
	}

	@JsonIgnore
	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getUsername() {
		return username;
	}

}
