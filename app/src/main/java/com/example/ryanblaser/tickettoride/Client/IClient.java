package com.example.ryanblaser.tickettoride.Client;

import UserInfo.User;
import UserInfo.Username;

import java.util.List;

import Command.CommandContainer;

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
    public CommandContainer login(String username, String password, String authenticationCode) throws InvalidUsername, InvalidPassword;
    public CommandContainer register(String username, String password, String authorizationCode) throws UsernameAlreadyExists;
    public CommandContainer addResumableGame(int gameId);
    public CommandContainer addJoinableGame(int gameId);
    public CommandContainer addWaitingGame(int gameId);
    public CommandContainer removeGame(int gameId);
    public CommandContainer startGame(int gameId, String authorizationCode);
    public CommandContainer addPlayer(Username username, int gameId);
    public CommandContainer logout(String str_authentication_code);
    public CommandContainer listJoinableGames(List<Integer> listJoinableGames);
    public CommandContainer listResumableGames(List<Integer> listResumableGames);
    public CommandContainer listWaitingGames(List<Integer> listWaitingGames);
    public CommandContainer loginRegisterSucceeded(User user, String authenticationCode);
    public CommandContainer logoutSucceeded();

    public void attachObserver(/* Observer object */);
    public void detachObserver(/* Observer object */);
}
