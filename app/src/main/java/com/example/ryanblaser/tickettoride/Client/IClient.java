package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.List;
import java.util.Set;

/**
 * Created by natha on 2/1/2017.
 */

public interface IClient {

    /*
     * Created own Exceptions for when checking login authorization
     */
    public static class InvalidUsername extends Exception {
    }

    public static class InvalidPassword extends Exception {
    }

    public static class UsernameAlreadyExists extends Exception {
    }

    //TODO: Should we change the return type to CommandContainer to match IServer?
    public void login(User user) throws InvalidUsername, InvalidPassword;
    public void register(User user) throws UsernameAlreadyExists;
    public void addResumableGame(Game game);
    public void addJoinableGame(Game game);
    public void addWaitingGame(Game game);
    public void removeGame(String gameId);
    public void startGame(Game game, int authorizationCode);
    public void addPlayer(Username username, String gameId);
    public void attachObserver(/* Observer object */);
    public void detachObserver(/* Observer object */);
    public void logout(int int_authentication_code);
    public void listJoinableGames(List<Game> listJoinableGames);
    public void listResumableGames(Set<Game> listResumableGames);
    public void listWaitingGames(Set<Game> listWaitingGames);
    public void loginRegisterSucceeded(User user, String authenticationCode);
    public void logoutSucceeded();

}
