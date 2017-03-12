package com.example.ryanblaser.tickettoride.Client;


import android.util.Log;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToServerCommand;

import com.example.ryanblaser.tickettoride.Command.Phase1.*;
import com.example.ryanblaser.tickettoride.Command.Phase2.BroadcastToChatCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.ClaimRouteCommand;
import com.example.ryanblaser.tickettoride.Command.Phase2.GetFaceUpTableTrainCardCommand;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.net.URL;
import java.util.List;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static com.example.ryanblaser.tickettoride.Client.ServerProxy SINGLETON = new com.example.ryanblaser.tickettoride.Client.ServerProxy();

    private ServerProxy() {

    }


    @Override
    public List<ICommand> login(User user) throws com.example.ryanblaser.tickettoride.Client.IClient.InvalidUsername, com.example.ryanblaser.tickettoride.Client.IClient.InvalidPassword {
        String urlSuffix = "/command";

        ICommand loginCommand = new LoginCommand(user);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, loginCommand);
            clientCommunicator.execute(url);

        } catch (Exception e) {
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

        ICommand addGameCommand = new AddGameToServerCommand(new Game(), str_authentication_code);

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
    public List<ICommand> removeGame(Game game) {
        String urlSuffix = "/command";

        ICommand removeGameCommand = new DeleteGameCommand(game.get_i_gameId());

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, removeGameCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //No need to return anything since the command execute methods access the clientmodel already
    }

    @Override
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame) {
        String urlSuffix = "/command";

        ICommand startGameCommand = new StartGameCommand(gameId, usernamesInGame);

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
    public List<ICommand> logout(String authenticationCode) {
        String urlSuffix = "/command";

        ICommand logoutCommand = new LogoutCommand(authenticationCode);

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


	@Override
	public List<ICommand> addGame(Game game) {
		// TODO Auto-generated method stub
		return null;
	}


    public ICommand broadcastToChat(String message) {
        String urlSuffix = "/command";

        ICommand broadcastToChat = new BroadcastToChatCommand(message);

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
    public List<ICommand> claimRoute(Route route) {
        String urlSuffix = "/command";

        ICommand claimRouteCommand = new ClaimRouteCommand(route);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, claimRouteCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ICommand> getFirstFaceUpTableTrainCardCommand(TrainCard trainCard, Boolean isWild) {
        String urlSuffix = "/command";

        ICommand getFirstFaceUpTableTrainCardCommand = new GetFaceUpTableTrainCardCommand(trainCard, isWild);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, getFirstFaceUpTableTrainCardCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}