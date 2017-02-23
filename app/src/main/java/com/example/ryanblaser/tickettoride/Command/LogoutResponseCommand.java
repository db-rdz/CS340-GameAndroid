package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LogoutResponseCommand implements ICommand{
  
  @Override
  public CommandContainer execute(){
    return ClientFacade.SINGLETON.logoutSucceeded();}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }
}
