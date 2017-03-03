package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListJoinableCommand implements ICommand { // sent to clients after login/registration

  @JsonProperty("list_Game_List")
  private List<Integer> list_game_list;
  private ListJoinableCommand(){}
  public ListJoinableCommand(List<Integer> list){
    list_game_list = list;}

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
    ClientFacade.SINGLETON.listJoinableGames(list_game_list);
    return null;
  }

  @JsonIgnore
  @Override
  public Game getGame() {
    return null;
  }
}
