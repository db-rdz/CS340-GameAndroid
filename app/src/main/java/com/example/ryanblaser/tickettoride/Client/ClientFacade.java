package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * This class accesses the data in the Client Holder/Model.
 * The ClientFacade talks to the ServerProxy class to use these methods.
 * The ClientFacade allows us to communicate to the server from the client side.
 *
 * Created by natha on 2/4/2017.
 *
 * The following note added by 0joshuaolson1 on 2/10/2017:
 * IClient should only have methods the server 'calls' on the ClientProx.
 * All other methods (without @Override) are called by presenters/fragments/MainActivity.
 */

public class ClientFacade implements IClient {


    public static ClientFacade SINGLETON = new com.example.ryanblaser.tickettoride.Client.ClientFacade();
    private ClientModel clientmodel;
    private LoginPresenter loginpresenter;
    private LobbyPresenter lobbypresenter;
    private Poller poller;


    private ClientFacade() {
        attachLoginObserver(LoginPresenter.SINGLETON);
        attachLobbyObserver(LobbyPresenter.SINGLETON);
        poller = new Poller();
    }

    /**
     * ClientModel is initilized in the MainActivity so no need to reinitialize it in the constructor
     */
    public void initilizeClientModel(MainActivity mainActivity) {
        this.clientmodel = new ClientModel(mainActivity);
    }

    @Override
    public void login(User user) throws InvalidUsername, InvalidPassword {
        try {
            ServerProxy.SINGLETON.login(user);

        } catch (InvalidPassword e) { //Catches exception if the login failed.
            throw new InvalidPassword();
        } catch (InvalidUsername e) {
            throw new InvalidUsername();
        }
        
    }

    public void register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists {
        try {
            ServerProxy.SINGLETON.register(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void addJoinableGame() {
        ServerProxy.SINGLETON.addJoinableGame(clientmodel.getUser().getStr_authentication_code());
        //lobbypresenter
		
    }

    @Override
    public void addWaitingGame(int gameId) {
        clientmodel.addWaitingGame(gameId);
        ServerProxy.SINGLETON.addPlayer(clientmodel.getUser().getStr_authentication_code(), gameId);
        lobbypresenter.switchToWaitingView();
        //lobbypresenter
		
    }

    @Override
    public void removeGame(int gameId) {
        clientmodel.deleteGame(gameId);
        //lobbypresenter
		
    }

    
    @Override
    public void startGame(int gameId, String authenticationCode) { // just gameId?
        ServerProxy.SINGLETON.startGame(gameId, authenticationCode);
        //lobbypresenter
        
    }

    public void addPlayerToModel(String str_authentication_code, int gameId) throws IServer.GameIsFullException { // which exception?
        ServerProxy.SINGLETON.addPlayer(str_authentication_code, gameId); //server will get username
        //lobbypresenter
        
    }

    @Override
    public void attachObserver() {

    }

    @Override
    public void detachObserver() {

    }

    @Override
    public void addPlayerToClientModel(String username, int gameId){
        clientmodel.addPlayer(username, gameId);
        //lobbypresenter
		
    }

    @Override
    public void addPlayerToServerModel(String authenticationCode, int gameId) {
        ServerProxy.SINGLETON.addPlayer(authenticationCode, gameId);
    }

    public void attachLobbyObserver(LobbyPresenter lobbyPresenter) { //necessary?
        this.lobbypresenter = lobbyPresenter;
    }
    public void attachLoginObserver(LoginPresenter loginPresenter) { //necessary?
        this.loginpresenter = loginPresenter;
    }

    public void logout(String authenticationCode) {
        ServerProxy.SINGLETON.logout(authenticationCode);
    }

    @Override
    public void listJoinableGames(List<Integer> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames); //TODO: Change type of game 
        //lobbypresenter
		
    }


    @Override
    public void listWaitingGames(List<Integer> listWaitingGames) {
        clientmodel.setWaitingGames(listWaitingGames);
        //lobbypresenter
		
    }

    @Override
    public void loginRegisterSucceeded(User user) {
        clientmodel.setAuthenticationKey(user.getStr_authentication_code());
        clientmodel.setUser(user);
        loginpresenter.switchToLobbyView();
        //change view/presenter
		
    }

    @Override
    public void logoutSucceeded() {

        clientmodel.setAuthenticationKey(null);
        clientmodel.setUser(null);
    	
    }

    @Override
    public void broadcastToChat(String message) {
        ServerProxy.SINGLETON.broadcastToChat(message);
    }

    @Override
    public void getDestinationCards() {
        
    }

    @Override
    public void selectRequestedDestinationCard() {
        
    }

    @Override
    public void showMessage(String message) {
        
    }

    @Override
    public void updateCarCount(int numOfCarsUsed) {
        clientmodel.updateCarCount(numOfCarsUsed);
        
    }

    @Override
    public void updatePoints(int pointsToAdd) {
        clientmodel.updatePoints(pointsToAdd);
        
    }

    @Override
    public void updateFaceUpTableTrainCards() {
        
    }

    @Override
    public void updatePlayerDestinationCards() {
        
    }

    @Override
    public void updatePlayerTrainCards() {
        
    }

    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}
    
    public ClientModel getClientModel() { return clientmodel; }

    public void attachLobbyObserver(LobbyFragment lobbyFragment) {
    }

    public Poller getPoller() { return poller; }

}