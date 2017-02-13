package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddWaitingToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  private AddWaitingToClientCommand(){}
  public AddWaitingToClientCommand(Game g){
    game = g;}

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
    ClientFacade.SINGLETON.addWaitingGame(game);}}
