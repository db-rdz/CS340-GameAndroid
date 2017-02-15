package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.List;
import java.util.Set;

public class ListJoinableCommand implements ICommand{ // sent to clients after login/registration
  private List<com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game> list_game_list;
  private ListJoinableCommand(){}
  public ListJoinableCommand(List<com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game> list){
    list_game_list = list;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public CommandContainer execute(){
    ClientFacade.SINGLETON.listJoinableGames(list_game_list); //TODO: Decide which Game class to use
    return null;
  }}
