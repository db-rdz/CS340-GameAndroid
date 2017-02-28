package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeleteGameCommand implements ICommand {
  private int int_game_id;
  private DeleteGameCommand(){}
  public DeleteGameCommand(int gameId){
	  int_game_id = gameId;}

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
    ClientFacade.SINGLETON.removeGame(int_game_id);
    return null; //TODO: stub
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
