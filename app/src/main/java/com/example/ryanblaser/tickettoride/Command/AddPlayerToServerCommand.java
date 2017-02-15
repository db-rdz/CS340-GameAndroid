package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddPlayerToServerCommand implements ICommand{
  private String str_game_id;
  private String str_authentication_code;
  private AddPlayerToServerCommand(){}
  public AddPlayerToServerCommand(String gameId, String k){
    str_game_id = gameId;
    str_authentication_code = k;}

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute(){ // auth key must be changed to Username somewhere
    ServerFacade.SINGLETON.addPlayer(str_authentication_code, str_game_id);
    return null; //TODO: stub
  }
  @Override
  public String getAuthenticationCode(){
    return str_authentication_code;}}
