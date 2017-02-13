package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.Set;

public class ListJoinableCommand implements ICommand{ // sent to clients after login/registration
  private Set<Game> set_game_list;
  private ListJoinableCommand(){}
  public ListJoinableCommand(Set<Game> list){
    set_game_list = list;}

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
    ClientFacade.SINGLETON.listJoinableGames(set_game_list);}}
