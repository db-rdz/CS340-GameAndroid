package com.example.ryanblaser.tickettoride.Client;

import java.util.List;


import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;

import com.example.ryanblaser.tickettoride.Server.IServer;

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

    public static class UserAlreadyLoggedIn extends Exception {

    }

    public CommandContainer login(User user) throws InvalidUsername, InvalidPassword;
    public CommandContainer register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists;
    public CommandContainer addResumableGame(int gameId);
    public CommandContainer addJoinableGame();
    public CommandContainer addWaitingGame(int gameId);
    public CommandContainer removeGame(int gameId);
    public CommandContainer startGame(int gameId, String authorizationCode);
    public CommandContainer addPlayer(String username, int gameId) throws IServer.GameIsFullException;
    public CommandContainer logout(String str_authentication_code);
    public CommandContainer listJoinableGames(List<Integer> listJoinableGames);
    public CommandContainer listResumableGames(List<Integer> listResumableGames);
    public CommandContainer listWaitingGames(List<Integer> listWaitingGames);
    public CommandContainer loginRegisterSucceeded(User user);
    public CommandContainer logoutSucceeded();

    // Phase 2 additions - Ryan Blaser
    public CommandContainer broadcastToChat(String message);
    public CommandContainer getDestinationCards();
    public CommandContainer selectRequestedDestinationCard();
    public CommandContainer showMessage(String message);
    public CommandContainer updateCarCount(int numOfCarsUsed);
    public CommandContainer updateFaceUpTableTrainCards();
    public CommandContainer updatePlayerDestinationCards();
    public CommandContainer updatePlayerTrainCards();
    public CommandContainer updatePoints(int pointsToAdd);

    public void attachObserver(/* Observer object */);
    public void detachObserver(/* Observer object */);
}
