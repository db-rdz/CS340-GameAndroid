package com.example.ryanblaser.tickettoride.Client;

import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.GUI.Activities.GameActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel.testDestinationCard;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Nathan
     * This will contain the data the user has as a Player.
     * This Player object will be sent to the Server, and this ClientModel will receive
     * a Player object from the server to update.
     */
    private Player current_player;

    /**
     * Nathan
     * This will contain the list of players in the game, but it won't contain which cards
     * each player has. Only the number of train and destination cards owned by the player.
     */
    private List<Player> listOfPlayersInGame;

    /**
     * Nathan
     *
     */
    private List<DestCard> listOfDestinationCards;



    public ClientModel(MainActivity mainActivity1){
        mainActivity = mainActivity1;
        gameActivity = null;
        user = null; //Not new user so the LoginFragment shows up first
        str_authentication_code = null;
        list_joinable = new ArrayList<>();
        list_waiting = new ArrayList<>();
        hashtable_id_to_list = new Hashtable<>();
        gameId_to_usernames = new Hashtable<>();
        boolean_is_creator_of_game = false;
        int_curr_gameId = 0;
        current_player = new Player();
        listOfPlayersInGame = new ArrayList<>();
        listOfDestinationCards = new ArrayList<>();
    }


    //Functions
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

    //Phase 2 functions
    public Pair<List<String>, HashMap<String, Player>> getInfoForExpandable(){
        List<Player> playerList =  getListOfPlayersInGame();
        List<String> usernameList = new ArrayList<>();
        HashMap<String, Player> info = new HashMap<>();
        for(int i = 0; i < playerList.size(); i++){
            String username = playerList.get(i).get_userName();
            usernameList.add(username);
            info.put(username, playerList.get(i));
        }

        Pair<List<String>, HashMap<String, Player>> adapterInfo =
                new Pair<>(usernameList, info);

        return adapterInfo;
    }



    //Getters and Setters
    public void setUser(User u){
        user = u;
    }
    public User getUser(){
        return user;
    }

    public String getStr_authentication_code() {
        return str_authentication_code;
    }
    public void setStr_authentication_code(String str_authentication_code) {
        this.str_authentication_code = str_authentication_code;
    }

    public void setJoinableGames(List<Integer> list){
        for(int gameId : list)
            addJoinableGame(gameId);
    }

    public List<Integer> getJoinableGames(){
        return list_joinable;
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

    public Player getCurrent_player() {
        return current_player;
    }
    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    public List<Player> getListOfPlayersInGame() {
        return listOfPlayersInGame;
    }
    public void setListOfPlayersInGame(List<Player> listOfPlayersInGame) {
        this.listOfPlayersInGame = listOfPlayersInGame;
    }

    public List<DestCard> getListOfDestinationCards() {
        return listOfDestinationCards;
    }
    public void setListOfDestinationCards(List<DestCard> listOfDestinationCards) {
        this.listOfDestinationCards = listOfDestinationCards;
    }
}