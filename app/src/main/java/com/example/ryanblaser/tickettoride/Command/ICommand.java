package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.List;

public interface ICommand {
    public CommandContainer execute();
    public String getAuthenticationCode();
    public User getUser();

}
