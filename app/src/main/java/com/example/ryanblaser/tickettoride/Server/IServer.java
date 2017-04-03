package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Client.User;

import java.util.List;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public interface IServer {


    public static class GameIsFullException extends Exception {
    }

    public List<ICommand> login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword;
    public List<ICommand> register(String username, String password) throws IClient.UsernameAlreadyExists;
    public int addJoinableGameToServer(String str_authentication_code);
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame, String authenticationCode);
    public List<ICommand> addPlayerToServerModel(String str_authentication_code, int gameId) throws GameIsFullException;
    public List<ICommand> logout(User user);

    //PHASE2
    public void claimRoute(Route route, String authenticationCode, int gameId, List<TrainCard> cardsUsed);
    public void getFaceUpTableTrainCardCommand(int gameId, String authenticationCode, int FirstSecondCardPick, int trainCardIndex, Boolean isWild);
    public void getTopDeckTrainCardCommand(int gameId, String authenticationCode, int firstSecondCardPick);
    public void rejectDestCard(int gameId, String authenticationCode, DestCard slidingDeckModel);
    public void firstTurn(int gameId, String authenticationCode, List<DestCard> destCardsToKeep, String type);
    public void getDestCards(int gameId, String authenticationCode);
    public void lastTurnCompleted(int gameId, String authenticationCode);

}