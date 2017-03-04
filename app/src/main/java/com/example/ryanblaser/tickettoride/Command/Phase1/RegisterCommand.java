package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RegisterCommand implements ICommand {
    private User user;

    private RegisterCommand() {
    }

    public RegisterCommand(User u) {
        user = u;
    }

//
    @Override
    public CommandContainer execute() {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }
    //   public RegisterCommand(String username, String password, String str_authentication_code) {
//    	user = new User();
//    	user.setUsername(username);
//    	user.setPassword(password);
//    	user.setStr_authentication_code(str_authentication_code);
//    }
//
//    @JsonIgnore
//    @Override
//    public String getAuthenticationCode() {
//        return null;
//    }

    @Override
    public User getUser() {
        return user;
    }

    @JsonIgnore
    @Override
    public Game getGame() {
        return null;
    }
//
//    @JsonIgnore
//    @Override
//    public Game getGame() {
//        return null;
//    }
}