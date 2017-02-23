package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LoginRegisterResponseCommand implements ICommand{
  private User user;
  private String str_authentication_code;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(String username, String password, String k){
	  user = new User();
    user.setUsername(username);
    user.setPassword(password);
    str_authentication_code = k;
    user.setStr_authentication_code(k);
    }


  @Override
  public User getUser() {
    return user;
  }
  
  @Override
  public CommandContainer execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user, str_authentication_code);
    return null;
    }
  @Override
  public String getAuthenticationCode(){
    return str_authentication_code;}}
