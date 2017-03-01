package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class CommandContainer{

  public List<String> str_type;
  public List<ICommand> icommand;
  public CommandContainer(){str_type = new ArrayList<>(); icommand = new ArrayList<>();}
  public CommandContainer(List<String> type, List<ICommand> cmd){
    str_type = type;
    icommand = cmd;}
  }