package com.example.ryanblaser.tickettoride.Client;

import android.graphics.Color;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.ArrayList;
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

    public List<TrainCard> fillListOfCards(int cardAmount, int rainbowAmount, int weight, String type)
    {
        List<TrainCard> cardsUsed = new ArrayList<>();
        int i = 0;
        while (i < cardAmount)
        {
            TrainCard newCard = new TrainCard();
            newCard.setType(type);
            cardsUsed.add(newCard);
            if (cardsUsed.size() < weight)
            {
                i++;
            }
            else {
                break;
            }
        }
        if (cardsUsed.size() < weight)
        {
            i = 0;
            while (i < rainbowAmount)
            {
                TrainCard newCard = new TrainCard();
                newCard.setType("RAINBOW");
                cardsUsed.add(newCard);
                if (cardsUsed.size() < weight)
                {
                    i++;
                }
                else {
                    break;
                }
            }
        }
        return cardsUsed;
    }

    public void claimRoute(Route routeToClaim) {
        String code = clientmodel.getStr_authentication_code();
        int gameId = clientmodel.getInt_curr_gameId();

        int carCount = clientmodel.getCurrent_player().get_car_count();
        List<TrainCard> cardsUsed = new ArrayList<>();
        PlayerCardHand playerHand = clientmodel.getPlayer_hand();

        int cardType = 0;
        switch(routeToClaim.get_S_Color()) { //Determines which train cards to use for claiming gray routes
            case "RED":
                cardType = playerHand.getRedCardAmount();
                break;
            case "ORANGE":
                cardType = playerHand.getOrangeCardAmount();
                break;
            case "YELLOW":
                cardType = playerHand.getYellowCardAmount();
                break;
            case "GREEN":
                cardType = playerHand.getGreenCardAmount();
                break;
            case "BLUE":
                cardType = playerHand.getBlueCardAmount();
                break;
            case "WHITE":
                cardType = playerHand.getWhiteCardAmount();
                break;
            case "BLACK":
                cardType = playerHand.getBlackCardAmount();
                break;
            case "RAINBOW":
                cardType = playerHand.getRainbowCardAmount();
                break;
            case "PINK":
                cardType = playerHand.getPinkCardAmount();
                break;
        }

        if (!routeToClaim.get_S_Color().equals("GRAY")) {
            cardsUsed = fillListOfCards(cardType, playerHand.getRainbowCardAmount(), routeToClaim.get_Weight(), routeToClaim.get_S_Color());
        } else {
            // determine color to use
        }

        //Check if the player has enough cards to claim the route
        if (carCount >= 3) { //TODO: put a check so LastTurnCommand is called once
            ServerProxy.SINGLETON.claimRoute(routeToClaim, code, gameId, cardsUsed);
        }
        else if (carCount < 3) {
            ServerProxy.SINGLETON.lastTurn(routeToClaim, code, gameId, cardsUsed);
        }

    }

    @Override
    public void getDestinationCards() {
        String code = clientmodel.getStr_authentication_code();
        int gameId = clientmodel.getInt_curr_gameId();
        ServerProxy.SINGLETON.getDestCards(gameId, code);
    }

    @Override
    public void getFaceUpTableTrainCardCommand(int FirstSecondCardPick, int id, Boolean isWild) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.getFaceUpTableTrainCardCommand(gameId, authenticationCode, FirstSecondCardPick, id, isWild);
    }

    @Override
    public void getTopDeckTrainCardCommand(int FirstSecondCardPick) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.getTopDeckTrainCardCommand(gameId, authenticationCode, FirstSecondCardPick);
    }

    @Override
    public void firstTurn(List<DestCard> destCardsToKeep, String type) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.firstTurn(gameId, authenticationCode, destCardsToKeep, type);
    }

    @Override
    public void rejectDestCard(DestCard slidingDeckModel) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.rejectDestCard(gameId, authenticationCode, slidingDeckModel);
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
    public void updatePlayerDestinationCards(List<DestCard> rejectedCards) {
        clientmodel.getPlayer_hand().rejectDestinationCards(rejectedCards);
        clientmodel.getCurrent_player().updateCurrentDestinationCards(rejectedCards.size());
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

    public void keepAllDestCards(List<DestCard> cards)
    {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.keepAllDestCards(gameId, authenticationCode, cards);
    }

    @Override
    public void removeCardsUsed(List<TrainCard> cardsUsed) {
        for (int i = 0; i < cardsUsed.size(); i++) {
            clientmodel.getPlayer_hand().subtractToCardCount(cardsUsed.get(i).getType());
        }
    }
}