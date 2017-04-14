package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class accesses the data in the Client Holder/Model.
 * The ClientFacade talks to the ServerProxy class to use these methods.
 * The ClientFacade allows us to communicate to the server from the client side.
 *
 * Created by Nathan on 2/4/2017.
 */

public class ClientFacade implements IClient {

    /**
     * A public access point to the Client Facade;
     */
    public static ClientFacade SINGLETON = new ClientFacade();

    /**
     * The client model is only accessable through the Client Facade to follow the facade pattern properly
     * It contains all the user data and player data when a game is started.
     */
    private ClientModel clientmodel;

    /**
     * The login observer so the program will be updated whenever the login view needs to be changed.
     */
    private LoginPresenter loginpresenter;

    /**
     * The login observer so the program will be updated whenever the lobby view needs to be changed.
     */
    private LobbyPresenter lobbypresenter;

    /**
     * The poller talkes to the server every few seconds and receives commands from the server.
     */
    private Poller poller;


    private ClientFacade() {
        clientmodel = new ClientModel();
        attachLoginObserver(LoginPresenter.SINGLETON);
        attachLobbyObserver(LobbyPresenter.SINGLETON);
    }

    /**
     * sends the server the username and password to the server to login.
     * This User is essentially a Data Transfer Object (DTO) from client to server.
     *
     * @param user A DTO to send the username and password to the server
     * @throws InvalidUsername If the username is invalid, this throws a custom exception
     * @throws InvalidPassword If the password is invalid, this throws a custom exception
     */
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

    /**
     *  Sends the server the username and password to the server to register the user.
     *
     * @param username Desired username of user
     * @param password Desiered password of user
     * @throws InvalidPassword If the password is invalid, this throws a custom exception
     * @throws InvalidUsername If the username is invalid, this throws a custom exception
     * @throws UsernameAlreadyExists
     */
    @Override
    public void register(String username, String password) throws InvalidPassword, InvalidUsername, UsernameAlreadyExists {
        try {
            ServerProxy.SINGLETON.register(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Simply tells the server to create a game on the server.
     * The player authentication code is sent so the server knows who created the game.
     */
    @Override
    public void addJoinableGameToServer() {
        ServerProxy.SINGLETON.addJoinableGameToServer(clientmodel.getUser().getStr_authentication_code());

    }

    /**
     * A SwitchToWaitingView command from the server calls this method.
     * It switches the player from the game lobby view to the game waiting view.
     */
    @Override
    public void switchToWaitingView() {
        lobbypresenter.switchToWaitingView();

    }

    /**
     * Tells the server to delete a game he's apart of
     * @param gameId The game to be deleted on the server
     */
    @Override
    public void removeGame(int gameId) {
        clientmodel.deleteGame(gameId);

    }

    /**
     * Sends a start game command to the server.
     * Tells the server which game to start, and all the players in the game.
     * @param gameId The specific game to be started
     * @param usernamesInGame The list of all the players in the game
     */
    @Override
    public void startGame(int gameId, List<String> usernamesInGame) {
        String code = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.startGame(gameId, usernamesInGame, code);

    }

//    public void addPlayerToModel(String str_authentication_code, int gameId) throws IServer.GameIsFullException {
//        ServerProxy.SINGLETON.addPlayerToServerModel(str_authentication_code, gameId); //server will get username
//
//    }

    /**
     * Sends a command to the server to add them to a specific game.
     *
     * @param authenticationCode The authentication code of the specific player. Used to confirm user on server
     * @param gameId The specific game to be started
     */
    @Override
    public void addPlayerToServerModel(String authenticationCode, int gameId) {
        ServerProxy.SINGLETON.addPlayerToServerModel(authenticationCode, gameId);
    }

    /**
     * Allows the Client Facade to access Lobby Presenter/Observer methods to follow the Model-View-Presenter pattern
     * @param lobbyPresenter An object of the Lobby Presenter
     */
    @Override
    public void attachLobbyObserver(LobbyPresenter lobbyPresenter) { //necessary?
        this.lobbypresenter = lobbyPresenter;
    }

    /**
     * Allows the Client Facade to access Login Presenter/Observer methods to follow the Model-View-Presenter pattern
     * @param loginPresenter An object of the Login Presenter
     */
    @Override
    public void attachLoginObserver(LoginPresenter loginPresenter) { //necessary?
        this.loginpresenter = loginPresenter;
    }

    /**
     * Sends a command to the server to logout the user.
     * The user authentication code will be null after this.
     *
     * @param authenticationCode The authentication code of the specific player. Used to confirm user on server
     */
    @Override
    public void logout(String authenticationCode) {
        ServerProxy.SINGLETON.logout(authenticationCode);
    }

    /**
     * Grabes the list of all joinable games on the server
     * @param listJoinableGames A list of Integers containing game ids
     */
    @Override
    public void listJoinableGames(List<Integer> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames);
		
    }

    /**
     * This method is always called from a LoginRegisterResponse command.
     * Whenver a user is logging in or registering for the first time, the user is logged in.
     * The poller starts running as soon as the user is logged in.
     * This method makes the view switch from the Login view to the Lobby view.
     *
     * @param user The object containing the username, password, and authentication code of the user
     */
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

    /**
     * This method is called from the LogoutResponse command.
     * This makes the Android project go all the way back to the login view and destroys previous views
     */
    @Override
    public void logoutSucceeded() {
        clientmodel.logout();
        clientmodel.getMainActivity().setLoginFragment(null);
        clientmodel.getMainActivity().getLobbyFragment().logout();
    }


    //Phase 2

    /**
     * Tells the server what toast or chat message to send to everyone else.
     * @param message The message the user is sending
     */
    @Override
    public void broadcastToChat(String message) {
        ServerProxy.SINGLETON.broadcastToChat(clientmodel.getInt_curr_gameId(), clientmodel.getStr_authentication_code(), message);
    }

    /**
     * Helper function for sending the list of train cards used.
     * Depending on the type of train card used, the method will take cards from the client model.
     * @param cardAmount The amount of a specific color train card needed.
     * @param rainbowAmount The amount of rainbow cards needed.
     * @param weight The route length = the amount of train cars to use.
     * @param type The card color type
     * @return A list of Train Cards to be used to claim the route through the server.
     */
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

    /**
     * Nathan: added trainCardColor param for help in claiming gray routes
     * @param routeToClaim The route the player is going to claim
     * @param trainCardColor Determines which color train cards to use in claiming a gray route
     */
    public void claimRoute(Route routeToClaim, String trainCardColor) {
        String code = clientmodel.getStr_authentication_code();
        int gameId = clientmodel.getInt_curr_gameId();

        List<TrainCard> cardsUsed = new ArrayList<>();
        List<DestCard> destCards = clientmodel.getPlayer_hand().get_destCards();
        PlayerCardHand playerHand = clientmodel.getPlayer_hand();

        int cardType = 0;
        switch(trainCardColor) { //Determines which train cards to use for claiming gray routes
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

        cardsUsed = fillListOfCards(cardType, playerHand.getRainbowCardAmount(), routeToClaim.get_Weight(), trainCardColor);


        //Check if the player has enough cards to claim the route
        ServerProxy.SINGLETON.claimRoute(routeToClaim, code, gameId, cardsUsed);
    }

    /**
     * Requests more destination cards from the server.
     */
    @Override
    public void getDestinationCards() {
        String code = clientmodel.getStr_authentication_code();
        int gameId = clientmodel.getInt_curr_gameId();
        ServerProxy.SINGLETON.getDestCards(gameId, code);
    }

    /**
     * Tells the server to give this user the train card they picked.
     *
     * @param FirstSecondCardPick If it's the 1st or 2nd train card picked
     * @param id The index of where the card is in the list
     * @param isWild Determines if the card is a rainbow card or not
     */
    @Override
    public void getFaceUpTableTrainCardCommand(int FirstSecondCardPick, int id, Boolean isWild) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.getFaceUpTableTrainCardCommand(gameId, authenticationCode, FirstSecondCardPick, id, isWild);
    }

    /**
     * Tells the server to give this user the train card they picked.
     *
     * @param FirstSecondCardPick If it's the 1st or 2nd train card picked
     */
    @Override
    public void getTopDeckTrainCardCommand(int FirstSecondCardPick) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.getTopDeckTrainCardCommand(gameId, authenticationCode, FirstSecondCardPick);
    }

    /**
     * This is only called on the very first turn of the game.
     * Tells the server which destination cards the user wants to claim.
     *
     * @param destCardsToKeep The destination cards the player wants to keep
     * @param type The operation the server will do
     */
    @Override
    public void firstTurn(List<DestCard> destCardsToKeep, String type) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.firstTurn(gameId, authenticationCode, destCardsToKeep, type);
    }

    /**
     * Tells the server that the rejected card should go back to the destination card deck on the server
     * @param slidingDeckModel The destination card being rejected
     */
    @Override
    public void rejectDestCard(DestCard slidingDeckModel) {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.rejectDestCard(gameId, authenticationCode, slidingDeckModel);
    }


    /**
     * Simply updates the car count of the player whenever a route is claimed.
     *
     * @param numOfCarsUsed The amount of cars used
     */
    @Override
    public void updateCarCount(int numOfCarsUsed) {
        clientmodel.getCurrent_player().updateCarCount(numOfCarsUsed);
        
    }


    /**
     * The method tells the server which destination cards the player chooses to keep
     * @param cards The list of the Destination cards being sent.
     */
    public void keepAllDestCards(List<DestCard> cards)
    {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.keepAllDestCards(gameId, authenticationCode, cards);
    }

    /**
     * This sends the type of train cards used so the client model properly subtracts the amount.
     * @param cardsUsed The specific train cards used to claim a route.
     */
    @Override
    public void removeCardsUsed(List<TrainCard> cardsUsed) {
        for (int i = 0; i < cardsUsed.size(); i++) {
            clientmodel.getPlayer_hand().subtractToCardCount(cardsUsed.get(i).getType());
        }
    }

    /**
     * This method is only used when the user has completed their last turn.
     * Tells the server the user's last turn is over.
     */
    @Override
    public void lastTurnCompleted() {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.lastTurnCompleted(gameId, authenticationCode);

    }

    /**
     * This method is only called once a player's car count is less than 3.
     * Tells the server to start the last turn of every player.
     */
    @Override
    public void initiateLastTurn() {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.initiateLastTurn(gameId, authenticationCode);

    }

    /**
     * This method is only called from the EndGame view where the user presses a button
     * to go back to the game lobby.
     * This tells the server that the game is ended.
     */
    @Override
    public void endGame() {
        int gameId = clientmodel.getInt_curr_gameId();
        String authenticationCode = clientmodel.getStr_authentication_code();
        ServerProxy.SINGLETON.endGame(gameId, authenticationCode);
    }

    //Getters and setters
    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}

    public ClientModel getClientModel() { return clientmodel; }

    public Poller getPoller() {
        return poller;
    }
}