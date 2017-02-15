package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddResumableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  private AddResumableToClientCommand(){}
  public AddResumableToClientCommand(Game g){
    game = g;}

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
    ClientFacade.SINGLETON.addResumableGame(game);
    return null; //TODO:stub
  }}
