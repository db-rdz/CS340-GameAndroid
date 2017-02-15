package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static ServerProxy SINGLETON = new ServerProxy();

    private ServerProxy() {

    }


    @Override
    public CommandContainer login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LoginCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new LoginCommand(user));

        CommandContainer loginCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, loginCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginCommand;
    }

    @Override
    public CommandContainer register(User user) throws IClient.UsernameAlreadyExists {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("RegisterCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new RegisterCommand(user));

        CommandContainer registerCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, registerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return registerCommand;
    }

    @Override
    public CommandContainer addGame(Game game) {
        return null;
    }

    @Override
    public CommandContainer removeGame(Game game) {
        return null;
    }


    @Override
    public CommandContainer addResumableGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddResumable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddResumableToClientCommand(game));

        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddJoinable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddJoinableToClientCommand(game));

        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddWaiting");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddWaitingToClientCommand(game));

        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }


    //    @Override
    public void removeGame(String gameId) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("DeleteGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new DeleteGameCommand(gameId));

        CommandContainer removeGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, removeGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandContainer startGame(Game game, String authorizationCode) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("StartGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new StartGameCommand(game, authorizationCode));

        CommandContainer startGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, startGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return startGameCommand;
    }

    @Override
    public CommandContainer addPlayer(Username username, String gameId) throws GameIsFullException {
        return null;
    }

    @Override
    public CommandContainer addPlayer(int intAuthenticationCode, String sGameId) throws GameIsFullException {
        return null;
    }

    @Override
    public CommandContainer addPlayer(String authenticationCode, String gameId) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddPlayer");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddPlayerToClientCommand(authenticationCode, gameId));

        CommandContainer addPlayerCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addPlayerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addPlayerCommand;
    }

    //Added these functions after seeing the Command package
    public CommandContainer logout(int int_authentication_code) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("Logout");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new LogoutCommand(int_authentication_code));

        CommandContainer logoutCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, logoutCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return logoutCommand;
    }

    public void listJoinableGames(Set<Game> listJoinableGames) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("ListJoinable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new ListJoinableCommand(listJoinableGames));

        CommandContainer listJoinableCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listJoinableCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listResumableGames(Set<Game> listResumableGames) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("ListResumable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new ListResumableCommand(listResumableGames));

        CommandContainer listResumableCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listResumableCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listWaitingGames(Set<Game> listWaitingGames) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("ListWaiting");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new ListWaitingCommand(listWaitingGames));

        CommandContainer listWaitingCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, listWaitingCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginRegisterSucceeded(User user, String authenticationCode) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LoginRegisterResponse");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new LoginRegisterResponseCommand(user, authenticationCode));

        CommandContainer loginRegisterResponseCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, loginRegisterResponseCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutSucceeded() {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LogoutResponse");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new LogoutResponseCommand());

        CommandContainer logoutResponseCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, logoutResponseCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommandContainer checkForCommands()
    {
        String urlSuffix = "/update";

        List<String> types = new ArrayList<>();
        types.add("GetCommandsCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new GetCommandsCommand());

        CommandContainer checkForCommands = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, checkForCommands);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}