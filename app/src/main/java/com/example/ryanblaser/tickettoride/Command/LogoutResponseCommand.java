package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LogoutResponseCommand implements ICommand{
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.logoutSucceeded();}

  @Override
  public int getAuthenticationCode() {
    return 0;
  }

  @Override
  public User getUser() {
    return null;
  }
}
