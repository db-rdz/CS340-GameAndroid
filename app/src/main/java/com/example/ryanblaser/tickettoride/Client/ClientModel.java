package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable; // HashMap but hopefully throws exceptions if concurrently modified


public class ClientModel{

    public enum GameType{
        JOINABLE, WAITING, RESUMABLE
    }

    private String str_authentication_code;
    private User user;
    private List<Integer> list_joinable, list_waiting, list_resumable;
    private Hashtable<Integer, GameType> hashtable_id_to_list; //Each gameId key has their own GameType value.
    private Hashtable<Integer, List<String>> gameId_to_usernames;
    private MainActivity mainActivity;

    public ClientModel(MainActivity mainActivity1){
        mainActivity = mainActivity1;
        list_joinable = new ArrayList<Integer>();
        list_waiting = new ArrayList<Integer>();
        list_resumable = new ArrayList<Integer>();
        hashtable_id_to_list = new Hashtable<Integer, GameType>();
        gameId_to_usernames = new Hashtable<Integer, List<String>>();
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

    public void setJoinableGames(List<Integer> list){
        for(int game : list)
            addJoinableGame(game);
    }

    public List<Integer> getJoinableGames(){
        return list_joinable;
    }

    public void addJoinableGame(int gameId){
        hashtable_id_to_list.put(gameId, GameType.JOINABLE);
        list_joinable.add(gameId);
    }

    public void setWaitingGames(List<Integer> list){
        for(int game : list)
            addWaitingGame(game);
    }

    public List<Integer> getWaitingGames(){
        return list_waiting;
    }

    public void addWaitingGame(int gameId){
        hashtable_id_to_list.put(gameId, GameType.WAITING);
        list_waiting.add(gameId);

    }

    public void setResumableGames(List<Integer> list){
        for(int game : list)
            addResumableGame(game);
    }

    public List<Integer> getResumableGames(){
        return list_resumable;
    }

    public void addResumableGame(int gameId){
        hashtable_id_to_list.put(gameId, GameType.RESUMABLE);
        list_resumable.add(gameId);
    }

    public GameType getGameType(int gameId){
        return hashtable_id_to_list.get(gameId);
    }

    public GameType deleteGame(int gameId){
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

    private void addPlayerToGameObject(String username, int gameId){
    	gameId_to_usernames.get(gameId).add(username);
    }

    public void addPlayer(String username, int gameId){
        GameType type = getGameType(gameId);
        if(type == GameType.JOINABLE){
            addPlayerToGameObject(username, gameId);
        }
        else if(type == GameType.WAITING){
            addPlayerToGameObject(username, gameId);
        }
        else {
            //do nothing for resumable type game
        }

    }

    public MainActivity getMainActivity() { return mainActivity; }

}