package com.example.ryanblaser.tickettoride.Client;


import android.util.Log;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToServerCommand;

import com.example.ryanblaser.tickettoride.Command.Phase1.*;
import com.example.ryanblaser.tickettoride.Command.Phase2.BroadcastToChatCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.ClaimRouteCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.FirstTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetDestinationCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetFaceUpTableTrainCardCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetTopDeckTrainCardCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.KeepAllDestCardsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.StartLastTurnCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.RejectDestinationCardCommand;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static ServerProxy SINGLETON = new ServerProxy();


    private ServerProxy() {
        //Need to have plugin functionality here?
    }


    @Override
    public List<ICommand> login(User user) throws com.example.ryanblaser.tickettoride.Client.IClient.InvalidUsername, com.example.ryanblaser.tickettoride.Client.IClient.InvalidPassword {
        String urlSuffix = "/command";

        ICommand loginCommand = new LoginCommand(user);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, loginCommand);
            clientCommunicator.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }

    @Override
    public List<ICommand> register(String username, String password) throws IClient.UsernameAlreadyExists {
        String urlSuffix = "/command";

        ICommand registerCommand = new RegisterCommand(new User(username, password));

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, registerCommand);
            clientCommunicator.execute(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }



    @Override
    public int addJoinableGameToServer(String str_authentication_code) {
        String urlSuffix = "/command";

        ICommand addGameCommand = new AddGameToServerCommand(str_authentication_code);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, addGameCommand);
            int cmd = clientCommunicator.execute(url).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame, String authenticationCode) {
        String urlSuffix = "/command";

        ICommand startGameCommand = new StartGameCommand(gameId, usernamesInGame, authenticationCode);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, startGameCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }

    @Override
    public List<ICommand> addPlayerToServerModel(String authenticationCode, int gameId) {
        String urlSuffix = "/command";

        ICommand addPlayerCommand = new AddPlayerToServerCommand(authenticationCode, gameId);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, addPlayerCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }

    @Override
    public List<ICommand> logout(User user) {
        String urlSuffix = "/command";

        ICommand logoutCommand = new LogoutCommand(user);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, logoutCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }

    public int checkForCommands(String username, int lastCommandRecievedIndex)
    {
        String urlSuffix = "/update";

        ICommand checkForCommands = new GetCommandsCommand(username,lastCommandRecievedIndex);
        Log.d("GetCommandsCommand", "Checking commands for " + username);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, checkForCommands);
            clientCommunicator.execute(url);
            int returnValue = clientCommunicator.get();
            return returnValue;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Nathan
     * This function allows the commands gotten with checkForCommands be deleted. So they're never repeated
     * @param
     */
//    public void deleteGottenCommands(String username) {
//        String urlSuffix = "/update";
//
//        ICommand deleteGottenCommands = new DeleteGottenCommands(username);
//        Log.d("DeleteGottenCommands", "Deleting commands for " + username);
//
//        try {
//            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
//            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, deleteGottenCommands);
//            clientCommunicator.execute(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



    public ICommand broadcastToChat(int gameId, String authenticationCode, String message) {
        String urlSuffix = "/command";

        ICommand broadcastToChat = new BroadcastToChatCommand(gameId, authenticationCode, message);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, broadcastToChat);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void claimRoute(Route route, String authenticationCode, int gameId, List<TrainCard> cardsUsed) {
        String urlSuffix = "/command";

        ICommand claimRouteCommand = new ClaimRouteCommand(gameId, authenticationCode, route, cardsUsed);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, claimRouteCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFaceUpTableTrainCardCommand(int gameId, String authenticationCode, int FirstSecondCardPick, int trainCardIndex, Boolean isWild) {
        String urlSuffix = "/command";

        ICommand getFirstFaceUpTableTrainCardCommand = new GetFaceUpTableTrainCardCommand(gameId, trainCardIndex, isWild, authenticationCode, FirstSecondCardPick);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, getFirstFaceUpTableTrainCardCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTopDeckTrainCardCommand(int gameId, String authenticationCode, int firstSecondCardPick) {
        String urlSuffix = "/command";

        ICommand getTopDeckTrainCardCommand = new GetTopDeckTrainCardCommand(gameId, authenticationCode, firstSecondCardPick);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, getTopDeckTrainCardCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rejectDestCard(int gameId, String authenticationCode, DestCard slidingDeckModel) {
        String urlSuffix = "/command";

        ICommand rejectDestCard = new RejectDestinationCardCommand(gameId, authenticationCode, slidingDeckModel);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, rejectDestCard);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void firstTurn(int gameId, String authenticationCode, List<DestCard> destCardsToKeep, String type) {
        String urlSuffix = "/command";

        ICommand firstTurn = new FirstTurnCommand(gameId, authenticationCode, destCardsToKeep, type);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, firstTurn);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keepAllDestCards(int gameId, String authenticationCode, List<DestCard> cards)
    {
        String urlSuffix = "/command";

        ICommand keepAll = new KeepAllDestCardsCommand(gameId, authenticationCode, cards);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, keepAll);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDestCards(int gameId, String authenticationCode) {
        String urlSuffix = "/command";

        ICommand getDestCards = new GetDestinationCardsCommand(gameId, authenticationCode);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, getDestCards);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startLastTurn(Route routeToClaim, String code, int gameId, List<TrainCard> cardsUsed) {
        String urlSuffix = "/command";

        ICommand lastTurn = new StartLastTurnCommand(gameId, code, routeToClaim, cardsUsed);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, lastTurn);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}