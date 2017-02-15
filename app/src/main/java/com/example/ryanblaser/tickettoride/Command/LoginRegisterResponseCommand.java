package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LoginRegisterResponseCommand implements ICommand{
  private User user;
  private String str_authentication_code;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(User u, String k){
    user = u;
    str_authentication_code = k;}


  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public CommandContainer execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user, str_authentication_code);}
  @Override
  public String getAuthenticationCode(){
    return str_authentication_code;}}
