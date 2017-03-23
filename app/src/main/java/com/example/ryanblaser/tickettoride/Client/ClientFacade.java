package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
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

 * IClient should only have methods the server 'calls' on the CProxy.
 * All other methods (without @Override) are called by presenters/fragments/MainActivity.
 */

public class ClientFacade implements IClient {


    public static ClientFacade SINGLETON = new ClientFacade();
    private ClientModel clientmodel;
    private LoginPresenter loginpresenter;
    private LobbyPresenter lobbypresenter;
    private Poller poller;


    private ClientFacade() {
        clientmodel = new ClientModel();
        attachLoginObserver(LoginPresenter.SINGLETON);
        attachLobbyObserver(LobbyPresenter.SINGLETON);
//        poller = new Poller();
    }

    /**
     * ClientModel is initilized in the MainActivity so no need to reinitialize it in the constructor
     */
//    public void initilizeClientModel(MainActivity mainActivity) {
//        this.clientmodel = new ClientModel(mainActivity);
//    }

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

    @Override
    public void register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists {
        try {
            ServerProxy.SINGLETON.register(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void addJoinableGameToServer() {
        ServerProxy.SINGLETON.addJoinableGameToServer(clientmodel.getUser().getStr_authentication_code());
//        lobbypresenter.refreshGameLobby();
        //lobbypresenter
		
    }

    @Override
    public void switchToWaitingView() {
        lobbypresenter.switchToWaitingView();
        //lobbypresenter
		
    }

    @Override
    public void removeGame(int gameId) {
        clientmodel.deleteGame(gameId);
        //lobbypresenter
		
    }

    
    @Override
    public void startGame(int gameId, List<String> usernamesInGame) { // just gameId?
        String code = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.startGame(gameId, usernamesInGame, code);
        //lobbypresenter
        
    }

    public void addPlayerToModel(String str_authentication_code, int gameId) throws IServer.GameIsFullException { // which exception?
        ServerProxy.SINGLETON.addPlayerToServerModel(str_authentication_code, gameId); //server will get username
        //lobbypresenter
        
    }

    @Override
    public void addPlayerToServerModel(String authenticationCode, int gameId) {
        ServerProxy.SINGLETON.addPlayerToServerModel(authenticationCode, gameId);
    }

    @Override
    public void attachLobbyObserver(LobbyPresenter lobbyPresenter) { //necessary?
        this.lobbypresenter = lobbyPresenter;
    }

    @Override
    public void attachLoginObserver(LoginPresenter loginPresenter) { //necessary?
        this.loginpresenter = loginPresenter;
    }

    @Override
    public void logout(User user) {
        ServerProxy.SINGLETON.logout(user);
        poller.logout(); //Stops the poller from working anymore
    }

    @Override
    public void listJoinableGames(List<Integer> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames); //TODO: Change type of game
//        lobbypresenter.refreshGameLobby();
        //lobbypresenter
		
    }

    @Override
    public void loginRegisterSucceeded(User user) {
        LoginPresenter.SINGLETON.showLoginMessage();
        clientmodel.setStr_authentication_code(user.getStr_authentication_code());
        clientmodel.setUser(user);
        poller = new Poller(); //Start poller if login is successful
        poller.setUser(user);
        loginpresenter.switchToLobbyView();
        //change view/presenter
		
    }

    @Override
    public void logoutSucceeded() {
        clientmodel.logout();
        clientmodel.backToLogin();
    }


    //Phase 2
    @Override
    public void broadcastToChat(String message) {
        ServerProxy.SINGLETON.broadcastToChat(clientmodel.getInt_curr_gameId(), clientmodel.getStr_authentication_code(), message);
    }

    @Override
    public void claimRoute(Route route) {
        String code = clientmodel.getStr_authentication_code();
        int gameId = clientmodel.getInt_curr_gameId();
        ServerProxy.SINGLETON.claimRoute(route, code, gameId);
    }

    @Override
    public void getDestinationCards() {
        
    }

    @Override
    public void selectRequestedDestinationCard() {
        
    }

    @Override
    public void showMessage(List<String> messages) {
        
    }

    @Override
    public void updateCarCount(int numOfCarsUsed) {
        clientmodel.getCurrent_player().updateCarCount(numOfCarsUsed);
        
    }

    @Override
    public void updatePoints(int pointsToAdd) {
        clientmodel.getCurrent_player().updatePoints(pointsToAdd);
        
    }

    @Override
    public void updateFaceUpTableTrainCards() {
        
    }

    @Override
    public void updatePlayerDestinationCards(int addDestCardAmount) {
        clientmodel.getCurrent_player().updateCurrentDestinationCards(addDestCardAmount);
    }

    @Override
    public void updatePlayerTrainCardAmount(int addTrainCardAmount) {
        clientmodel.getCurrent_player().updateCurrentTrainCards(addTrainCardAmount);
    }

    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}
    
    public ClientModel getClientModel() { return clientmodel; }

    public void attachLobbyObserver(LobbyFragment lobbyFragment) {
    }

    public Poller getPoller() { return poller; }

}