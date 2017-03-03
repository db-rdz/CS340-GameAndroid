package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddResumableToClientCommand implements ICommand { // sent after changes from what List... commands sent
  private int gameId;
  private AddResumableToClientCommand(){}
  public AddResumableToClientCommand(int g){
    gameId = g;}

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
  public CommandContainer execute(){
    return ClientFacade.SINGLETON.addResumableGame(gameId);
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
