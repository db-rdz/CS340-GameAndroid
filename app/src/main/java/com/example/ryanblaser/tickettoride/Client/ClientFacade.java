package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.LobbyFragment;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.Server.ServerProxy;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.Set;

/**
 * This class accesses the data in the Client Holder/Model.
 * The ClientFacade talks to the ServerProxy class to use these methods.
 * The ClientFacade allows us to communicate to the server from the client side.
 *
 * Created by natha on 2/4/2017.
 *
 * The following note added by 0joshuaolson1 on 2/10/2017:
 * IClient should only have methods the server 'calls' on the ClientProxy.
 * All other methods (without @Override) are called by presenters/fragments/MainActivity.
 */

public class ClientFacade implements IClient {


    public static ClientFacade SINGLETON = new ClientFacade();
    private Set<User> set_users;
    private ClientModel clientmodel;

    private boolean checkUsername(User u) { // must be 3-10 characters (from our Android keyboard locale)
        int length = u.getUsername().length();
        return length >= 3 && length <= 10;
    }

    private boolean checkPassword(User u) { // must be 5-10 characters (from our Android keyboard locale)
        int length = u.getPassword().length();
        return length >= 5 && length <= 10;
    }
    
    private ClientFacade(){
        clientmodel = new ClientModel();
    }

    public void login(User user) throws InvalidUsername, InvalidPassword {
        if(!checkUsername(user))
            throw new InvalidUsername();
        if(!checkPassword(user))
            throw new InvalidPassword();
        try {
            ServerProxy.SINGLETON.login(user);

        } catch (Exception e) { //Catches exception if the login failed.
            e.printStackTrace();
        }
    }

    public void register(User user) throws InvalidUsername, InvalidPassword, UsernameAlreadyExists {
        if(!checkUsername(user))
            throw new InvalidUsername();
        if(!checkPassword(user))
            throw new InvalidPassword();
        try {
            ServerProxy.SINGLETON.register(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResumableGame(Game game) {
        clientmodel.addResumableGame(game);
    }

    @Override
    public void addJoinableGame(Game game) {
        clientmodel.addJoinableGame(game);
    }

    @Override
    public void addWaitingGame(Game game) {
        clientmodel.addWaitingGame(game);
    }

    @Override
    public void removeGame(String gameId) {
        clientmodel.deleteGame(gameId);
    }

    public void startGame(Game game) { // just gameId?
        //clientmodel gets authentication code
        ServerProxy.SINGLETON.startGame(game, authenticationCode);
    }

    public void addPlayer(String gameId) throws IServer.GameIsFullException {
        //clientmodel gets authentication code
        ServerProxy.SINGLETON.addPlayer(int_authentication_code, gameId); //server will get username
        //lobbypresenter
    }

    @Override
    public void addPlayer(Username username, String gameId){
        clientmodel.addPlayer(username, gameId);
    }

    public void attachLobbyObserver(LobbyFragment f) {
        clientmodel.attachLobbyObserver(f);
    }

    public void detachObserver() { // necessary?
        //lobbypresenter
    }

    public void logout() {
        //clientmodel gets authentication code
        ServerProxy.SINGLETON.logout(authenticationCode);
    }

    @Override
    public void listJoinableGames(Set<Game> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames);
    }

    @Override
    public void listResumableGames(Set<Game> listResumableGames) {
        clientmodel.setResumableGames(listResumableGames);
    }

    @Override
    public void listWaitingGames(Set<Game> listWaitingGames) {
        clientmodel.setWaitingGames(listWaitingGames);
    }

    @Override
    public void loginRegisterSucceeded(User user, String authenticationCode) {
        clientmodel.setAuthenticationKey(authenticationCode);
        clientmodel.setUser(user);
        //change view/presenter
    }

    @Override
    public void logoutSucceeded() {
        //change view/presenter
    }

    public User find(User user) {
        for (User userSearch : set_users) { //Searches all the users in the set.
            if (userSearch.equals(user)) { //If the username is in the set,
                return userSearch; //return the user found
            }
        }

        return user; //Otherwise return the user that was initially searched for.
    }

    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}

}
