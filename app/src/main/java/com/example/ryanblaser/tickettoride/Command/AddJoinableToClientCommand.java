package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddJoinableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  private AddJoinableToClientCommand(){}
  public AddJoinableToClientCommand(Game g){
    game = g;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute() {
//    ClientFacade.SINGLETON.addJoinableGame(game);} //TODO:fix the return type
    return null;
  }
}
