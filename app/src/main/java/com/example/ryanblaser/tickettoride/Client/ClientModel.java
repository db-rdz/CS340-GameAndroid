package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.Game;
import com.example.ryanblaser.tickettoride.GUI.Activities.GameActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;

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
    private GameActivity gameActivity;
    private int int_car_count;
    private int int_total_points;

    public ClientModel(MainActivity mainActivity1){
        mainActivity = mainActivity1;
        gameActivity = null;
        list_joinable = new ArrayList<Integer>();
        list_waiting = new ArrayList<Integer>();
        list_resumable = new ArrayList<Integer>();
        hashtable_id_to_list = new Hashtable<Integer, GameType>();
        gameId_to_usernames = new Hashtable<Integer, List<String>>();
        int_car_count = 45; //Each player starts with 45 train cars
        int_total_points = 0;
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

    public void setJoinableGames(List<com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game> list){
        for(com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game game : list)
            addJoinableGame(game.get_i_gameId());
    }

    public List<Integer> getJoinableGames(){
        return list_joinable;
    }

    public void addJoinableGame(int gameId){
        hashtable_id_to_list.put(gameId, GameType.JOINABLE);
        list_joinable.add(gameId);
//        LobbyPresenter.SINGLETON.refreshGameLobby();

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

    public void addPlayerToModel(String username, int gameId){
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

    /**
     * Nathan
     * Simply adds points to the player's current points.
     *
     * @return
     */
    public void updatePoints(int pointsToAdd) {
        int_total_points += pointsToAdd;
    }

    /**
     * Nathan
     * Subtracts the player's current train car amount from the amount of cars used.
     * @param numOfCarsUsed
     */
    public void updateCarCount(int numOfCarsUsed) {
        int_car_count -= numOfCarsUsed;
    }

    public MainActivity getMainActivity() { return mainActivity; }

    /**
     * Nathan: GameActivity methods
     * For grabbing the onResume() method for screen refreshing
     * @return
     */
    public GameActivity getGameActivity() { return gameActivity; }
    public void setGameActivity(GameActivity gameActivity) { this.gameActivity = gameActivity; }

    public int getInt_car_count() {
        return int_car_count;
    }

    public void setInt_car_count(int int_car_count) {
        this.int_car_count = int_car_count;
    }

    public int getInt_total_points() {
        return int_total_points;
    }

    public void setInt_total_points(int int_total_points) {
        this.int_total_points = int_total_points;
    }

    public String getStr_authentication_code() {
        return str_authentication_code;
    }

    public List<Integer> getList_joinable() {
        return list_joinable;
    }

    public List<Integer> getList_waiting() {
        return list_waiting;
    }

    public List<Integer> getList_resumable() {
        return list_resumable;
    }

    public Hashtable<Integer, GameType> getHashtable_id_to_list() {
        return hashtable_id_to_list;
    }

    public Hashtable<Integer, List<String>> getGameId_to_usernames() {
        return gameId_to_usernames;
    }
}