package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LogoutCommand implements ICommand{
  private String str_authentication_code;
  private LogoutCommand(){}
  public LogoutCommand(String k){
    str_authentication_code = k;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public CommandContainer execute(){
    return ServerFacade.SINGLETON.logout(str_authentication_code);
    }}
