package com.example.ryanblaser.tickettoride.Command;

import com.example.ryanblaser.tickettoride.UserInfo.User;

import java.util.List;

import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;

public interface ICommand {
    public CommandContainer execute() throws GameIsFullException;
    public String getAuthenticationCode();
    public User getUser();

}
