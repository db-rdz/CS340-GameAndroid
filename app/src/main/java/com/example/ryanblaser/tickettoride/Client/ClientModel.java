package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.LobbyFragment;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Hashtable; // HashMap but hopefully throws exceptions if concurrently modified

class ClientModel{

    public enum GameType{
        JOINABLE, WAITING, RESUMABLE
    }

    private String str_authentication_code;
    private User user;
    private Hashtable<String, Game> list_joinable, list_waiting, list_resumable;
    private Hashtable<String, GameType> hashtable_id_to_list;

    private LobbyFragment lobbyPresenter = null;

    public ClientModel(){
        list_joinable = new Hashtable<String, Game>();
        list_waiting = new Hashtable<String, Game>();
        list_resumable = new Hashtable<String, Game>();
        hashtable_id_to_list = new Hashtable<String, GameType>();
    }

    public void attachLobbyObserver(LobbyFragment f){
        lobbyPresenter = f;
    }

    public void setAuthenticationKey(String k){
        str_authentication_code = k;
    }

    public String getAuthenticationKey(){
        return str_authentication_code;
    }

    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public void setJoinableGames(Set<Game> list){
        for(Game game : list)
            addJoinableGame(game);
    }

    public Collection<Game> getJoinableGames(){
        return list_joinable.values();
    }

    public void addJoinableGame(Game game){
        hashtable_id_to_list.put(game.get_s_game_id(), GameType.JOINABLE);
        list_joinable.put(game.get_s_game_id(), game);
    }

    public void setWaitingGames(Set<Game> list){
        for(Game game : list)
            addWaitingGame(game);
    }

    public Collection<Game> getWaitingGames(){
        return list_waiting.values();
    }

    public void addWaitingGame(Game game){
        hashtable_id_to_list.put(game.get_s_game_id(), GameType.WAITING);
        list_waiting.put(game.get_s_game_id(), game);
    }

    public void setResumableGames(Set<Game> list){
        for(Game game : list)
            addResumableGame(game);
    }

    public Collection<Game> getResumableGames(){
        return list_resumable.values();
    }

    public void addResumableGame(Game game){
        hashtable_id_to_list.put(game.get_s_game_id(), GameType.RESUMABLE);
        list_resumable.put(game.get_s_game_id(), game);
    }

    public GameType getGameType(String gameId){
        return hashtable_id_to_list.get(gameId);
    }

    public GameType deleteGame(String gameId){
        GameType type = getGameType(gameId);
        hashtable_id_to_list.remove(gameId);
        if(type == GameType.JOINABLE)
            list_joinable.remove(gameId);
        else if(type == GameType.WAITING)
            list_waiting.remove(gameId);
        else
            list_resumable.remove(gameId);
        return type;
    }

    private void addPlayerToGameObject(Username username, Game game){
        List<Username> usernames = game.getPlayers();
        usernames.add(username);
        game.setPlayers(usernames);
    }

    public void addPlayer(Username username, String gameId){
        Game game;
        GameType type = getGameType(gameId);
        if(type == GameType.JOINABLE){
            game = list_joinable.get(gameId).clone();
            list_joinable.remove(gameId);
            addPlayerToGameObject(username, game);
            list_joinable.put(gameId, game);
        }
        else if(type == GameType.WAITING){
            game = list_waiting.get(gameId).clone();
            list_waiting.remove(gameId);
            addPlayerToGameObject(username, game);
            list_waiting.put(gameId, game);
        }
        else{
            game = list_resumable.get(gameId).clone();
            list_resumable.remove(gameId);
            addPlayerToGameObject(username, game);
            list_resumable.put(gameId, game);
        }

    }
}
