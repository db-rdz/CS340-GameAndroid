package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.ListResumableCommand;
import com.example.ryanblaser.tickettoride.Command.ListWaitingCommand;
import com.example.ryanblaser.tickettoride.Command.LoginCommand;
import com.example.ryanblaser.tickettoride.Command.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Command.RegisterCommand;
import com.example.ryanblaser.tickettoride.Command.StartGameCommand;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.Set;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static ServerProxy SINGLETON = new ServerProxy();

    private ServerProxy() {

    }


    @Override
    public void login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword {
        String urlSuffix = "/command";
        CommandContainer loginCommand = new CommandContainer("LoginCommand", new LoginCommand(user));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, loginCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(User user) throws IClient.UsernameAlreadyExists {
        String urlSuffix = "/command";
        CommandContainer registerCommand = new CommandContainer("RegisterCommand", new RegisterCommand(user));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, registerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addResumableGame(Game game) {
        String urlSuffix = "/command";
        CommandContainer addGameCommand = new CommandContainer("AddResumable", new AddResumableToClientCommand(game));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addJoinableGame(Game game) {
        String urlSuffix = "/command";
        CommandContainer addGameCommand = new CommandContainer("AddJoinable", new AddJoinableToClientCommand(game));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addWaitingGame(Game game) {
        String urlSuffix = "/command";
        CommandContainer addGameCommand = new CommandContainer("AddWaiting", new AddWaitingToClientCommand(game));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeGame(Game game) {

    }

//    @Override
    public void removeGame(String gameId) {
        String urlSuffix = "/command";
        CommandContainer removeGameCommand = new CommandContainer("DeleteGame", new DeleteGameCommand(gameId));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, removeGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGame(Game game, int authorizationCode) {
        String urlSuffix = "/command";
        CommandContainer startGameCommand = new CommandContainer("StartGame", new StartGameCommand(game, authorizationCode));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, startGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPlayer(Username username, String gameId) throws GameIsFullException {
        //DO WE NEED THIS ONE??
    }

    @Override
    public void addPlayer(int authenticationCode, String gameId) {
        String urlSuffix = "/command";
        CommandContainer addPlayerCommand = new CommandContainer("AddPlayer", new AddPlayerToClientCommand(authenticationCode, gameId));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addPlayerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Added these functions after seeing the Command package
    public void logout(int int_authentication_code) {
        String urlSuffix = "/command";
        CommandContainer logoutCommand = new CommandContainer("Logout", new LogoutCommand(int_authentication_code));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, logoutCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listJoinableGames(Set<Game> listJoinableGames) {
        String urlSuffix = "/command";
        CommandContainer listJoinableCommand = new CommandContainer("ListJoinable", new ListJoinableCommand(listJoinableGames));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listJoinableCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listResumableGames(Set<Game> listResumableGames) {
        String urlSuffix = "/command";
        CommandContainer listResumableCommand = new CommandContainer("ListResumable", new ListResumableCommand(listResumableGames));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listResumableCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listWaitingGames(Set<Game> listWaitingGames) {
        String urlSuffix = "/command";
        CommandContainer listWaitingCommand = new CommandContainer("ListWaiting", new ListWaitingCommand(listWaitingGames));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listWaitingCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginRegisterSucceeded(User user, int authenticationCode) {
        String urlSuffix = "/command";
        CommandContainer loginRegisterResponseCommand = new CommandContainer("LoginRegisterResponse", new LoginRegisterResponseCommand(user, authenticationCode));

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, loginRegisterResponseCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutSucceeded() {
        String urlSuffix = "/command";
        CommandContainer logoutResponseCommand = new CommandContainer("LogoutResponse", new LogoutResponseCommand());

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, logoutResponseCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
