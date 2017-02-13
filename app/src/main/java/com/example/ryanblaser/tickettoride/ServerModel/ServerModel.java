package com.example.ryanblaser.tickettoride.ServerModel;

import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.iGame;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class ServerModel implements iModel {

    public static ServerModel SINGLETON = new ServerModel();

    @Override
    public List<Game> getAvailableGames() {
        return Game.get_allAvailableGames();
    }

    @Override
    public List<Game> getStartedGames() {
        return Game.get_allStartedGames();
    }

    @Override
    public List<Game> getAllGames() {
        return Game.getAllGames();
    }

    @Override
    public List<Game> getUserJoinedGames( int userId ){
        return User.getUserWithID(userId).getJoinedGames();
    }

    @Override
    public Boolean initializeGameFromDB( int userId, int gameId ){
        User.getUserWithID(userId).initializeGame(gameId);
        return true;
    }

    @Override
    public Boolean addPlayerToGame(int userID, int gameID) {
        if(Game.isUserInGame(userID, gameID)){
            return false;
        }
        if(Game.isGameFull(gameID)){
            return false;
        }

        Game game = Game.getGameWithId(gameID);
        User.getUserWithID(userID).addGameToJoinedGames(game);
        return true;
    }

    @Override
    public Boolean removePlayerFromGame(int userID, int gameID) {
        return Game.getGameWithId(gameID).removePlayer(userID);
    }

    @Override
    public void removeAllPlayersFromGame(int gameID) {
        Game.getGameWithId(gameID).removeAllPlayer();
    }

    @Override
    public Boolean addGameToLobby(iGame game) {
        return null;
    }

    @Override
    public Boolean logIn(int userId) {
        return User.addLoggedInUser(userId);
    }

    @Override
    public Boolean logOut(int userId){
        return false;
    } //NOT USED IN SERVERFACADE

    public void logout(int int_authentication_code) { //USED IN SERVERFACADE
    }


    public Game getGame(String gameId) {
        return null;
    }
}
