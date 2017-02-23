package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LoginCommand implements ICommand {
    private User user;

    private LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }
    
    public LoginCommand(String username, String password, String str_authentication_code) {
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setStr_authentication_code(str_authentication_code);
    }

    @Override
    public CommandContainer execute() {
        try {
            ServerFacade.SINGLETON.login(user.getUsername(), user.getPassword());
        } catch (IClient.InvalidUsername invalidUsername) {
            invalidUsername.printStackTrace();
        } catch (IClient.InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return user.getStr_authentication_code();
    }

    @Override
    public User getUser() {
        return user;
    }
}