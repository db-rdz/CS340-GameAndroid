package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddJoinableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private int gameId;
  private AddJoinableToClientCommand(){}
  public AddJoinableToClientCommand(int g){
    gameId = g;}

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
    ClientFacade.SINGLETON.getClientModel().addJoinableGame(gameId);
    return null;
  }
}
