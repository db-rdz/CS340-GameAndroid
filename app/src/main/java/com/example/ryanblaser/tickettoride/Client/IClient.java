package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;

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
    public void logout(User user);
    public void listJoinableGames(List<Integer> listJoinableGames);
    public void loginRegisterSucceeded(User user);
    public void logoutSucceeded();
    public void attachLoginObserver(LoginPresenter loginPresenter);
    public void attachLobbyObserver(LobbyPresenter lobbyPresenter);


    // Phase 2 additions - Ryan Blaser
    public void broadcastToChat(String message);
    public void claimRoute(Route route);
    public void getDestinationCards();
    public void firstTurn(List<DestCard> destCardsToKeep, String type);
    public void getFaceUpTableTrainCardCommand(int FirstSecondCardPick, int id, Boolean isWild);
    public void getTopDeckTrainCardCommand(int FirstSecondCardPick);
    public void rejectDestCard(DestCard slidingDeckModel);
    public void updateCarCount(int numOfCarsUsed);
    public void updatePlayerDestinationCards(List<DestCard> rejectedCards);
    public void updatePlayerTrainCardAmount(int addTrainCardAmount);
    public void updatePoints(int pointsToAdd);
    public void removeCardsUsed(List<TrainCard> cardsUsed);
    public void lastTurnCompleted();
    public void initiateLastTurn();


}
