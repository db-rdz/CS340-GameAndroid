package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
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
    public List<ICommand> addGame(Game game);
    public int addJoinableGameToServer(String str_authentication_code);
    public List<ICommand> removeGame(Game game);
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame, String authenticationCode);
    public List<ICommand> addPlayerToServerModel(String str_authentication_code, int gameId) throws GameIsFullException;
    public List<ICommand> logout(String str_authentication_code);

    //PHASE2
    public List<ICommand> claimRoute(Route route, String authenticationCode, int gameId);
    public List<ICommand> getFaceUpTableTrainCardCommand(int gameId, Boolean isWild, int trainCardIndex);
}