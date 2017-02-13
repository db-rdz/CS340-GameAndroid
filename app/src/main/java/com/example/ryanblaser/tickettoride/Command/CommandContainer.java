package com.example.ryanblaser.tickettoride.Command;
public class CommandContainer{
  public String str_type;
  public ICommand icommand;
  private CommandContainer(){}
  public CommandContainer(String type, ICommand cmd){
    str_type = type;
    icommand = cmd;}}
