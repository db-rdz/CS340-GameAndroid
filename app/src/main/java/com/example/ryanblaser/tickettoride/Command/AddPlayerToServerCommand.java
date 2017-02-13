package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddPlayerToServerCommand implements ICommand{
  private String str_game_id;
  private int int_authentication_code;
  private AddPlayerToServerCommand(){}
  public AddPlayerToServerCommand(String gameId, int k){
    str_game_id = gameId;
    int_authentication_code = k;}

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public void execute(){ // auth key must be changed to Username somewhere
    ServerFacade.SINGLETON.addPlayer(int_authentication_code, str_game_id);}
  @Override
  public int getAuthenticationCode(){
    return int_authentication_code;}}
