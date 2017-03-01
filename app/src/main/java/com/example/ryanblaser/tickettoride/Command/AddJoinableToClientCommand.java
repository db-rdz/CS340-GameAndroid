package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddJoinableToClientCommand implements ICommand { // sent after changes from what List... commands sent
  private int gameId;
  private Game game;
  private AddJoinableToClientCommand(){}
  public AddJoinableToClientCommand(Game g){
    game = g;}

  @JsonIgnore
  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @JsonIgnore
  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute() {
    ClientFacade.SINGLETON.getClientModel().addJoinableGame(gameId);
    return null;
  }

  @Override
  public Game getGame() {
    return game;
  }
}
