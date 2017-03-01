package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.IClient;
<<<<<<< HEAD
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
=======
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.Client.User;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public interface IServer {

    public static class GameIsFullException extends Exception {
    }

    CommandContainer login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword;
    CommandContainer register(String username, String password) throws IClient.UsernameAlreadyExists;
    CommandContainer addGame(Game game);
    public CommandContainer addResumableGame(int gameId);
<<<<<<< HEAD
    public int addJoinableGame();
=======
    public int addJoinableGame(String str_authentication_code);
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
    public CommandContainer addWaitingGame(int gameId);
    CommandContainer removeGame(Game game);
    CommandContainer startGame(int gameId, String str_authentication_code);
    public CommandContainer addPlayer(String str_authentication_code, int gameId) throws GameIsFullException;
    public CommandContainer logout(String str_authentication_code);
}