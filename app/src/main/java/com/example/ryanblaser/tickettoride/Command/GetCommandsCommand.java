package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.ClientProxy;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GetCommandsCommand implements ICommand {
  private List<String> list_icommands;
  public GetCommandsCommand(){}
  public GetCommandsCommand(List<String> list){
    list_icommands = list;}

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
    return ClientProxy.SINGLETON.getUserCommands(getUser().getUsername());
  }

  @Override
  public Game getGame() {
    return null;
  }
}