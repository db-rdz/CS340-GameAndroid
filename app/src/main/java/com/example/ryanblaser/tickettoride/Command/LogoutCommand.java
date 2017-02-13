package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LogoutCommand implements ICommand{
  private int int_authentication_code;
  private LogoutCommand(){}
  public LogoutCommand(int k){
    int_authentication_code = k;}

  @Override
  public int getAuthenticationCode() {
    return 0;
  }

  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public void execute(){
    ServerFacade.SINGLETON.logout(int_authentication_code);}}
