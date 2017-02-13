package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LoginRegisterResponseCommand implements ICommand{
  private User user;
  private int int_authentication_code;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(User u, int k){
    user = u;
    int_authentication_code = k;}


  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user, int_authentication_code);}
  @Override
  public int getAuthenticationCode(){
    return int_authentication_code;}}
