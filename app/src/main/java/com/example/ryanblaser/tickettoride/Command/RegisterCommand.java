package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class RegisterCommand implements ICommand {
    private User user;

    private RegisterCommand() {
    }

    public RegisterCommand(User u) {
        user = u;
    }

    @Override
    public CommandContainer execute() {
        try {
            ServerFacade.SINGLETON.register(user);
        } catch (com.example.ryanblaser.tickettoride.Client.IClient.UsernameAlreadyExists usernameAlreadyExists) {
            usernameAlreadyExists.printStackTrace();
        }
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }
}