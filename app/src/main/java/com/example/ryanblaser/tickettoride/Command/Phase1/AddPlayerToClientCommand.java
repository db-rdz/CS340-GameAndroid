package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class AddPlayerToClientCommand implements ICommand { // sent after changes from what List... commands sent
  private int int_game_id;
  private String str_username;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(String name, Integer gameId){
    str_username = name;
    int_game_id = gameId;}

  @JsonIgnore
  @Override
  public String getAuthenticationCode() {
    return null;
  }
  
  public String getUsername()
  {
	  return str_username;
  }

  @JsonIgnore
  @Override
  public User getUser() {
    return null;
  }

  @Override
  public List<ICommand> execute() throws GameIsFullException {
    ClientFacade.SINGLETON.addPlayer(str_username, int_game_id);
    return null;
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
