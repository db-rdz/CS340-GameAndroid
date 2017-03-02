package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;



import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;

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


    public static com.example.ryanblaser.tickettoride.Client.ClientFacade SINGLETON = new com.example.ryanblaser.tickettoride.Client.ClientFacade();
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
    public CommandContainer login(User user) throws InvalidUsername, InvalidPassword {
        try {
            return ServerProxy.SINGLETON.login(user);

        } catch (InvalidPassword e) { //Catches exception if the login failed.
            throw new InvalidPassword();
        } catch (InvalidUsername e) {
            throw new InvalidUsername();
        }
        
    }

    public CommandContainer register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists {
        try {
            return ServerProxy.SINGLETON.register(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CommandContainer addResumableGame(int gameId) {
        clientmodel.addResumableGame(gameId);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer addJoinableGame() {
        ServerProxy.SINGLETON.addJoinableGame(clientmodel.getUser().getStr_authentication_code());
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer addWaitingGame(int gameId) {
        clientmodel.addWaitingGame(gameId);
        lobbypresenter.switchToWaitingView();
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer removeGame(int gameId) {
        clientmodel.deleteGame(gameId);
        //lobbypresenter
		return null;
    }

    
    @Override
    public CommandContainer startGame(int gameId, String authenticationCode) { // just gameId?
        ServerProxy.SINGLETON.startGame(gameId, authenticationCode);
        //lobbypresenter
        return null;
    }

    public CommandContainer addPlayerToModel(String str_authentication_code, int gameId) throws IServer.GameIsFullException { // which exception?
        ServerProxy.SINGLETON.addPlayer(str_authentication_code, gameId); //server will get username
        //lobbypresenter
        return null;
    }

    @Override
    public void attachObserver() {

    }

    @Override
    public void detachObserver() {

    }

    @Override
    public CommandContainer addPlayer(String username, int gameId){
        clientmodel.addPlayer(username, gameId);
        //lobbypresenter
		return null;
    }

    public void attachLobbyObserver(LobbyPresenter lobbyPresenter) { //necessary?
        this.lobbypresenter = lobbyPresenter;
    }
    public void attachLoginObserver(LoginPresenter loginPresenter) { //necessary?
        this.loginpresenter = loginPresenter;
    }

    public CommandContainer logout(String authenticationCode) {
        return ServerProxy.SINGLETON.logout(authenticationCode);
    }

    @Override
    public CommandContainer listJoinableGames(List<Integer> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames); //TODO: Change type of game 
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer listResumableGames(List<Integer> listResumableGames) {
        clientmodel.setResumableGames(listResumableGames);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer listWaitingGames(List<Integer> listWaitingGames) {
        clientmodel.setWaitingGames(listWaitingGames);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer loginRegisterSucceeded(User user) {
        clientmodel.setAuthenticationKey(user.getStr_authentication_code());
        clientmodel.setUser(user);
        loginpresenter.switchToLobbyView();
        //change view/presenter
		return null;
    }

    @Override
    public CommandContainer logoutSucceeded() {

        clientmodel.setAuthenticationKey(null);
        clientmodel.setUser(null);
    	return null;
    }

    @Override
    public CommandContainer broadcastToChat(String message) {
        return ServerProxy.SINGLETON.broadcastToChat(message);
    }

    @Override
    public CommandContainer getDestinationCards() {
        return null;
    }

    @Override
    public CommandContainer selectRequestedDestinationCard() {
        return null;
    }

    @Override
    public CommandContainer showMessage(String message) {
        return null;
    }

    @Override
    public CommandContainer updateCarCount() {
        return null;
    }

    @Override
    public CommandContainer updatePoints(int pointsToAdd) {
        return null;
    }

    @Override
    public CommandContainer updateFaceUpTableTrainCards() {
        return null;
    }

    @Override
    public CommandContainer updatePlayerDestinationCards() {
        return null;
    }

    @Override
    public CommandContainer updatePlayerTrainCards() {
        return null;
    }

    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}
    
    public ClientModel getClientModel() { return clientmodel; }

    public void attachLobbyObserver(LobbyFragment lobbyFragment) {
    }

    public Poller getPoller() { return poller; }

}