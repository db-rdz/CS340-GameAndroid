package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LogoutResponseCommand implements ICommand {
  
  @Override
  public CommandContainer execute(){
    return ClientFacade.SINGLETON.logoutSucceeded();}

  @JsonIgnore
  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
