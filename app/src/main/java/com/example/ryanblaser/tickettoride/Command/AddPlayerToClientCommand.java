package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private String str_game_id;
  private String str_authentication_code;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(String code, String gameId){
    str_authentication_code = code;
    str_game_id = gameId;}

  @Override
  public String getAuthenticationCode() {
    return str_authentication_code;
  }

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute(){
//    ClientFacade.SINGLETON.addPlayer(str_authentication_code, str_game_id);
    return null; //TODO: stub
  }}
