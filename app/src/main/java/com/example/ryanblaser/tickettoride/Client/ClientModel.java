package com.example.ryanblaser.tickettoride.Client;

import android.content.Intent;
import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel.Board;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.GUI.Activities.BoardActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.WaitingActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.Client.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.NOT_YOUR_TURN;

/**
 * The ClientModel class will contain all the information needed for the logged in user.
 * This class contains the information as a User and a Player (in game), and activities as well.
 * When the game is started, the GameBoardPresenter will store all the info in the client model,
 * and the client model will tell the GameBoardPresenter which data to show in the GUI.
 */
public class ClientModel{
//    /**
//     * This determines what player action was COMPLETED.
//     * If a player picks a train card, the player's state goes to PICKING_1ST_TRAIN,
//     * if the player claims a route during his turn, the state goes to CLAIMING_ROUTE.
//     */
//    public enum State {
//        YOUR_TURN, NOT_YOUR_TURN, CLAIMING_ROUTE, PICKING_DEST, PICKING_1ST_TRAIN, PICKING_2ND_TRAIN, FIRST_TURN, LAST_TURN, END_GAME;
//    }

    /**
     * Authentication code of the current user
     */
    private String str_authentication_code;

    /**
     * User information of the current user
     * This will only contain the username and authentication_code of the user.
     */
    private User user;

    /**
     * These lists contain the gameId's for the game that the current user is apart of
     */
    private List<Integer> list_joinable;

    /**
     * The client stores the gameId of a specific game, and then stores a list of Strings (usernames)
     * per gameId.
     * <pre>
     * key: Integer The gameId received from the server
     * </pre>
     * @value List The List of usernames
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
    private WaitingActivity waitingActivity;

    private BoardActivity boardActivity;

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
     * This stores the data as a Player once a game is started.
     * The Player will contain all the player's train and destination cards
     */
    private Player current_player;

    private List<String> chat;

    private PlayerCardHand player_hand;

    /**
     * Nathan:
     * Determines what player action was just completed
     */
    private State state;

    private List<Scoreboard> scoreboard;

    //Constructor
    public ClientModel() {
        waitingActivity = null;
        user = null;
        current_player = null;
        list_joinable = new ArrayList<>();
        gameId_to_usernames = new Hashtable<>();
        boolean_is_creator_of_game = false;
        int_curr_gameId = 0;
        chat = new ArrayList<>();
        player_hand = new PlayerCardHand();
        state = NOT_YOUR_TURN;
        scoreboard = new ArrayList<>();
    }

    /**
     * This will be initialized through the ClientFacade in the MainActivty.
     * <pre>
     * pre: mainActivity1 can't be null.
     * post: All the variables in this clrass must be initialized. User and waitingActivity MUST be null.
     *
     * </pre>
     *
     * @param mainActivity1 the context of the MainActivity
     *
     */
//    public ClientModel(MainActivity mainActivity1){
//        mainActivity = mainActivity1;
//        waitingActivity = null;
//        user = null;
//        current_player = null;
//        list_joinable = new ArrayList<>();
//        gameId_to_usernames = new Hashtable<>();
//        boolean_is_creator_of_game = false;
//        int_curr_gameId = 0;
//        chat = new ArrayList<>();
//        list_dest_cards = null;
//        list_players_in_game = null;
//        player_hand = new PlayerCardHand();
//        state = NOT_YOUR_TURN;
//    }


    /**
     * The method does a deep copy of the list being passed in.
     * The deep copy avoids issues of the data changing randomly.
     * <pre>
     *  pre: List can't be null
     *  post: the same gameId can't be in the list more than once
     *
     * </pre>
     *
     * @param list The list that contains the joinable games
     *
     */
    public void setJoinableGames(List<Integer> list){
        for(int gameId : list)
            addJoinableGame(gameId);
    }

    public List<Integer> getJoinableGames(){
        return list_joinable;
    }

    /**
     * The gameId is put into the map of gameIds and its gameType.
     * The gameId is also put into the list of joinable games so the user can see which
     * games they can join in the game lobby.
     * <pre>
     * pre: gameId can't be less than 1
     * post: the same gameId can't be in the list more than once
     *
     * </pre>
     *
     * @param gameId Specific gameId given from the server
     *

     */
    public void addJoinableGame(int gameId){
        list_joinable.add(gameId);
    }

    /**
     * Deletes the specific gameId from the list.
     * <pre>
     * pre: gameId can't be null and the map can't be null
     * post: The return value must be either JOINABLE or WAITING
     *
     * </pre>
     *
     * @param gameId Specific gameId received from server
     * @return If the game was Joinable or not
     *
     */
    public void deleteGame(int gameId){
        list_joinable.remove(gameId);

    }

    /**
     * Adds a username to a game represented by a gameId to the map.
     * If map doesn't contain the gameId, it is put into the map along with the username
     * If the map does contain the gameId, the username is instantly added to the map with the
     * corresponding gameId.
     * <pre>
     * pre: username can't be empty/null, gameId can't be less than 1
     * post: the map shouldn't contain multiple instances of the same username
     *
     * </pre>
     *  @param username User in the game
     * @param gameId Specific gameId given from server
     *
     */
    public void addPlayerToGame(String username, int gameId){
        if (gameId_to_usernames.containsKey(gameId) && !gameId_to_usernames.get(gameId).contains(username)) { //If game exists
    	    gameId_to_usernames.get(gameId).add(username);
        }
        else if (!gameId_to_usernames.containsKey(gameId)) { //If game doesn't exist yet
            List<String> gameCreator = new ArrayList<>();
            gameCreator.add(username); //Need to make a new List<String> for Map.put()
            gameId_to_usernames.put(gameId, gameCreator);
        }
    }

    /**
     * Nathan
     * Makes everything null.
     */
    public void logout() {
        current_player = null;
        int_curr_gameId = -1;
        player_hand = null;
        user = null;
        gameId_to_usernames.clear();
        mainActivity.logout();
        waitingActivity = null;
        str_authentication_code = "";
        list_joinable.clear();
        chat.clear();
        state = NOT_YOUR_TURN;

    }

    public void backToLogin() {
        Intent intent = new Intent(mainActivity.getBaseContext(), MainActivity.class);
        mainActivity.startActivity(intent);
    }

    //---------------------------------------- PHASE 2

    /**
     * Nathan: Used for the PlayerInfoFragment
     * @return A Pair of a list of usernames and a list of scoreboards for each individual player
     */
    public Pair<List<String>, List<Scoreboard>> getInfoForExpandable(){
        List<String> usernameList = gameId_to_usernames.get(int_curr_gameId);
        LinkedHashMap<String, Scoreboard> info = new LinkedHashMap<>();

        for(int i = 0; i < usernameList.size(); i++){
            info.put(usernameList.get(i), scoreboard.get(i)); //Each name will be mapped to their scoreboard
        }

        return new Pair<>(usernameList, scoreboard);
    }

    //Getters and Setters
    public String getStr_authentication_code() {
        return str_authentication_code;
    }

    public void setStr_authentication_code(String str_authentication_code) {
        this.str_authentication_code = str_authentication_code;
    }

    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public List<Integer> getList_joinable() {
        return list_joinable;
    }

    public void setList_joinable(List<Integer> list_joinable) {
        this.list_joinable = list_joinable;
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

    public WaitingActivity getWaitingActivity() {
        return waitingActivity;
    }

    public void setWaitingActivity(WaitingActivity waitingActivity) {
        this.waitingActivity = waitingActivity;
    }

    public BoardActivity getBoardActivity() {
        return boardActivity;
    }

    public void setBoardActivity(BoardActivity boardActivity) {
        this.boardActivity = boardActivity;
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

    public List<String> getChat() {
        return chat;
    }

    public void setChat(List<String> chat) {
        this.chat = chat;
    }

    public PlayerCardHand getPlayer_hand() {
        return player_hand;
    }

    public void setPlayer_hand(PlayerCardHand player_hand) {
        this.player_hand = player_hand;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Scoreboard> getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(List<Scoreboard> scoreboard) {
        this.scoreboard = scoreboard;
    }
}