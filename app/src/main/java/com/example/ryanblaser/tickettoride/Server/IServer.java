package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.List;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public interface IServer {

    public static class GameIsFullException extends Exception {
    }

    CommandContainer login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword;
    CommandContainer register(User user) throws IClient.UsernameAlreadyExists;
    CommandContainer addGame(Game game);
    CommandContainer removeGame(Game game);
    CommandContainer startGame(Game game, String str_authentication_code);
    CommandContainer addPlayer(Username username, String gameId) throws GameIsFullException;

    CommandContainer addPlayer(int intAuthenticationCode, String sGameId) throws GameIsFullException;

    public CommandContainer addResumableGame(Game game);
    public CommandContainer addJoinableGame(Game game);
    public CommandContainer addWaitingGame(Game game);
    public CommandContainer addPlayer(String str_authentication_code, String gameId);
    public CommandContainer logout(int int_authentication_code);
}