package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;

import java.util.List;

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

    public void login(User user) throws InvalidUsername, InvalidPassword;
    public void register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists;
    public void addJoinableGameToServer();
    public void switchToWaitingView();
    public void removeGame(int gameId);
    public void startGame(int gameId, List<String> usernamesInGame);
    public void addPlayerToServerModel(String authenticationCode, int gameId);
    public void logout(String str_authentication_code);
    public void listJoinableGames(List<Integer> listJoinableGames);
    public void loginRegisterSucceeded(User user);
    public void logoutSucceeded();


    // Phase 2 additions - Ryan Blaser
    public void broadcastToChat(String message);
    public void claimRoute(Route route);
    public void getDestinationCards();
    public void selectRequestedDestinationCard();
    public void showMessage(List<String> messages);
    public void updateCarCount(int numOfCarsUsed);
    public void updateFaceUpTableTrainCards();
    public void updatePlayerDestinationCards(int addDestCardAmount);
    public void updatePlayerTrainCardAmount(int addTrainCardAmount);
    public void updatePoints(int pointsToAdd);


    public void attachObserver(/* Observer object */);
    public void detachObserver(/* Observer object */);
}
