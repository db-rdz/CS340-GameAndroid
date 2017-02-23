package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private int int_game_id;
  private String str_username;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(String name, Integer gameId){
    str_username = name;
    int_game_id = gameId;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }
  
  public String getUsername()
  {
	  return str_username;
  }

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute() throws GameIsFullException{
    ClientFacade.SINGLETON.addPlayer(str_username, int_game_id);
    return null; //TODO: stub
  }}
