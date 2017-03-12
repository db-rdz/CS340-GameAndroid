package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.Activities.GameActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable; // HashMap but hopefully throws exceptions if concurrently modified


public class ClientModel{

    public enum GameType{
        JOINABLE, WAITING
    }

    /**
     * Authentication code of the current user
     */
    private String str_authentication_code;

    /**
     * User information of the current user
     */
    private User user;

    /**
     * These lists contain the gameId's for the game that the current user is apart of
     */
    private List<Integer> list_joinable, list_waiting;

    /**
     * Each gameId key has their own GameType value;
     */
    private Hashtable<Integer, GameType> hashtable_id_to_list;

    /**
     * The client stores the gameId of a specific game, and then stores a list of Strings (usernames)
     * per gameId.
     * @key Integer The gameId received from the server
     * @key List The List of usernames
     *
     */
    private Hashtable<Integer, List<String>> gameId_to_usernames;

    /**
     * MainActivity used to call methods from it.
     * Used to update the list of games in the lobby
     */
    private MainActivity mainActivity;

    /**
     * MainActivity used to call methods from it.
     * Used to update the list of games in the lobby
     */
    private GameActivity gameActivity;

    /**
     * Total car count of the curreent user.
     * Always start with 45 cars
     */
    private int int_car_count;

    /**
     * Total points the player has.
     * Always start with 0
     */
    private int int_total_points;

    /**
     * This determines if a player is the creator of the game.
     * If true, The player can see the Start Game button in the Game Activity Lobby
     * If false, The player can't see the Start Game button
     */
    private Boolean boolean_is_creator_of_game;

    /**
     * Nathan
     * Since there's only one game a user can join, we need this variable.
     */
    private int int_curr_gameId;

    public ClientModel(MainActivity mainActivity1){
        mainActivity = mainActivity1;
        gameActivity = null;
        list_joinable = new ArrayList<>();
        list_waiting = new ArrayList<>();
        hashtable_id_to_list = new Hashtable<>();
        gameId_to_usernames = new Hashtable<>();
        int_car_count = 45; //Each player starts with 45 train cars
        int_total_points = 0;
        boolean_is_creator_of_game = false;
        int_curr_gameId = 0;
    }

    public void setAuthenticationKey(String k){
        str_authentication_code = k;
    }


    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public void setJoinableGames(List<Integer> list){
        for(int gameId : list)
            addJoinableGame(gameId);
    }

    public List<Integer> getJoinableGames(){
        return list_joinable;
    }

    public void addJoinableGame(int gameId){
        hashtable_id_to_list.put(gameId, GameType.JOINABLE);
        list_joinable.add(gameId);
    }

    public void setWaitingGames(List<Integer> list){
        for(int gameId : list)
            addWaitingGame(gameId);
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

    /**
     * Deletes the specific gameId from the map.
     * @param gameId
     * @return
     */
    public GameType deleteGame(int gameId){
        GameType type = getGameType(gameId);
        hashtable_id_to_list.remove(gameId);
        if(type == GameType.JOINABLE) {
            list_joinable.remove(gameId);
        }
        else {//(type == GameType.WAITING)
            list_waiting.remove(gameId);
        }
        return type;
    }

    /**
     * Adds a username to a game represented by a gameId to the map
     * @param username
     * @param gameId
     */
    public void addPlayerToGameObject(String username, int gameId){
        if (gameId_to_usernames.containsKey(gameId) && !gameId_to_usernames.get(gameId).contains(username)) { //If game exists
    	    gameId_to_usernames.get(gameId).add(username);
        }
        else if (!gameId_to_usernames.containsKey(gameId)) { //If game doesn't exist yet
            List<String> gameCreator = new ArrayList<>();
            gameCreator.add(username); //Need to make a new List<String> for Map.put()
            gameId_to_usernames.put(gameId, gameCreator);
        }
    }

    public void addPlayerToModel(String username, int gameId){
        GameType type = getGameType(gameId);

        if(type == GameType.JOINABLE){
            addPlayerToGameObject(username, gameId);
        }
        else { //(type == GameType.WAITING)
            addPlayerToGameObject(username, gameId);
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

    //Getters and Setters

    public String getStr_authentication_code() {
        return str_authentication_code;
    }

    public void setStr_authentication_code(String str_authentication_code) {
        this.str_authentication_code = str_authentication_code;
    }

    public List<Integer> getList_joinable() {
        return list_joinable;
    }

    public void setList_joinable(List<Integer> list_joinable) {
        this.list_joinable = list_joinable;
    }

    public List<Integer> getList_waiting() {
        return list_waiting;
    }

    public void setList_waiting(List<Integer> list_waiting) {
        this.list_waiting = list_waiting;
    }

    public Hashtable<Integer, GameType> getHashtable_id_to_list() {
        return hashtable_id_to_list;
    }

    public void setHashtable_id_to_list(Hashtable<Integer, GameType> hashtable_id_to_list) {
        this.hashtable_id_to_list = hashtable_id_to_list;
    }

    public Hashtable<Integer, List<String>> getGameId_to_usernames() {
        return gameId_to_usernames;
    }

    public void setGameId_to_usernames(Hashtable<Integer, List<String>> gameId_to_usernames) {
        this.gameId_to_usernames = gameId_to_usernames;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

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

    public Boolean getBoolean_is_creator_of_game() {
        return boolean_is_creator_of_game;
    }

    public void setBoolean_is_creator_of_game(Boolean boolean_is_creator_of_game) {
        this.boolean_is_creator_of_game = boolean_is_creator_of_game;
    }

    public int getInt_curr_gameId() {
        return int_curr_gameId;
    }

    public void setInt_curr_gameId(int int_curr_gameId) {
        this.int_curr_gameId = int_curr_gameId;
    }
}