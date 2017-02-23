package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.ClientProxy;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.List;
public class GetCommandsCommand implements ICommand{
  private List<String> list_icommands;
  public GetCommandsCommand(){}
  public GetCommandsCommand(List<String> list){
    list_icommands = list;}

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
    return ClientProxy.SINGLETON.getUserCommands(new Username(getUser().getUsername()));
  }}