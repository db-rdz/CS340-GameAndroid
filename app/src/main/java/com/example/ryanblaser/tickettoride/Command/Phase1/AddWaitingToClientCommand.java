package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddWaitingToClientCommand implements ICommand { // sent after changes from what List... commands sent
  @JsonIgnore
  private int gameId;
  @JsonProperty("game")
  private Game game;
  public AddWaitingToClientCommand(){}
  public AddWaitingToClientCommand(Game g){
	  game = g;}

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
  public List<ICommand> execute(){

    for (int i = 1; i <= game.get_numberOfPlayers(); i++) {
      ClientFacade.SINGLETON.getClientModel().addPlayerToGameObject(game.getPlayer(i).get_S_username(), game.get_i_gameId());
    }

    ClientFacade.SINGLETON.addWaitingGame(game.get_i_gameId());
    return null; //Since client side is all void
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return game;
  }

  public int getGameId() {
    return gameId;
  }

}
