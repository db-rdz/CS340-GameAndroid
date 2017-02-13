package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class DeleteGameCommand implements ICommand{
  private String str_game_id;
  private DeleteGameCommand(){}
  public DeleteGameCommand(String gameId){
    str_game_id = gameId;}

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
    ClientFacade.SINGLETON.removeGame(str_game_id);}}
