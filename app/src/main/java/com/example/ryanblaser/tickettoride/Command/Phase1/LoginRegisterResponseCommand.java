package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginRegisterResponseCommand implements ICommand {
  private User user;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(User user){
    this.user = user;
    }


  @Override
  public User getUser() {
    return user;
  }
  
  @Override
  public CommandContainer execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user);
    return null;
    }

  @JsonIgnore
  @Override
  public String getAuthenticationCode(){
    return null;}

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
