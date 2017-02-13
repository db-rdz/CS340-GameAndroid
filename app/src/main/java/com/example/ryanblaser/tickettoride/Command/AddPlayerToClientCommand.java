package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

public class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private String str_game_id;
  private int int_authentication_code;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(int code, String gameId){
    int_authentication_code = code;
    str_game_id = gameId;}

  @Override
  public int getAuthenticationCode() {
    return int_authentication_code;
  }

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addPlayer(int_authentication_code, str_game_id);}}
