package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GetCommandsCommand implements ICommand {
  private String username;
  public GetCommandsCommand(){}
  public GetCommandsCommand(String username){
    this.username = username;}

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
  public List<ICommand> execute(){
    return null;
  }

  @Override
  public Game getGame() {
    return null;
  }

  public String getUsername()
  {
    return username;
  }
}