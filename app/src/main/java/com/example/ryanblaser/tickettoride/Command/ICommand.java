package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.List;

public interface ICommand {
    public void execute();
    public int getAuthenticationCode();
    public User getUser();

}
