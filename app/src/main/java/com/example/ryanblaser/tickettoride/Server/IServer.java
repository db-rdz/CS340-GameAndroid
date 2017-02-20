package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.UserInfo.Username;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.*;

import java.util.List;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public interface IServer {

    public static class GameIsFullException extends Exception {
    }

    CommandContainer login(String username, String password, String authoritzationCode) throws IClient.InvalidUsername, IClient.InvalidPassword;
    CommandContainer register(String username, String password, String authorizationCode) throws IClient.UsernameAlreadyExists;
    CommandContainer addGame(Game game);
    public CommandContainer addResumableGame(int gameId);
    public CommandContainer addJoinableGame(int gameId);
    public CommandContainer addWaitingGame(int gameId);
    CommandContainer removeGame(Game game);
    CommandContainer startGame(int gameId, String str_authentication_code);
    public CommandContainer addPlayer(String str_authentication_code, int gameId) throws GameIsFullException;
    public CommandContainer logout(String str_authentication_code);
}