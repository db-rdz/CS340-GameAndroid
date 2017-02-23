package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class RegisterCommand implements ICommand {
    private User user;

    private RegisterCommand() {
    }

    public RegisterCommand(User u) {
        user = u;
    }

    public RegisterCommand(String username, String password, String str_authentication_code) {
    	user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setStr_authentication_code(str_authentication_code);
    }
    
    @Override
    public CommandContainer execute() {
        try {
            return ServerFacade.SINGLETON.register(user.getUsername(), user.getPassword());
        } catch (IClient.UsernameAlreadyExists usernameAlreadyExists) {
            usernameAlreadyExists.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return user;
    }
}