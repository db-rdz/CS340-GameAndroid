package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class LoginCommand implements ICommand {
    private User user;

    private LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }

    @Override
    public void execute() {
        try {
            ServerFacade.SINGLETON.login(user);
        } catch (com.example.ryanblaser.tickettoride.Client.IClient.InvalidUsername invalidUsername) {
            invalidUsername.printStackTrace();
        } catch (com.example.ryanblaser.tickettoride.Client.IClient.InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }
    }

    @Override
    public int getAuthenticationCode() {
        return 0;
    }

    @Override
    public User getUser() {
        return null;
    }
}