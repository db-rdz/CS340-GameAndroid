package com.example.ryanblaser.tickettoride.Command;

import java.util.List;

public class CommandContainer{
  public List<String> str_type;
  public List<ICommand> icommand;
  private CommandContainer(){}
  public CommandContainer(List<String> type, List<ICommand> cmd){
    str_type = type;
    icommand = cmd;}}