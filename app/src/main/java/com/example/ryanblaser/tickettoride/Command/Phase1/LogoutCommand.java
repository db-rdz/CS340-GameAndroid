package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LogoutCommand implements ICommand {
  private String str_authentication_code;
  private LogoutCommand(){}
  public LogoutCommand(String k){
    str_authentication_code = k;}

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
    return ServerFacade.SINGLETON.logout(str_authentication_code);
    }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
