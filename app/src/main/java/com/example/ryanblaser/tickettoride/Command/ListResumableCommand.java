package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

import java.util.List;

public class ListResumableCommand implements ICommand{ // sent to clients after login/registration
  private List<Integer> list_game_list;
  private ListResumableCommand(){}
  public ListResumableCommand(List<Integer> list){
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
    ClientFacade.SINGLETON.listResumableGames(list_game_list); //TODO: Decide which Game class to use
    return null; //TODO:stub
  }}
