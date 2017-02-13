package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.List;
public class GetCommandsCommand implements ICommand{
  private List<ICommand> list_icommands;
  public GetCommandsCommand(){}
  public GetCommandsCommand(List<ICommand> list){
    list_icommands = list;}

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
    for(ICommand command : list_icommands)
      command.execute();}}
