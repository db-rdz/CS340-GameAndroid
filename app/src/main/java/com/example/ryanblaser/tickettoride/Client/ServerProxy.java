package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.*;
<<<<<<< HEAD
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
=======
import com.example.ryanblaser.tickettoride.Command.Phase1.AddGameToServerCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
//import Command.AddResumableToClientCommand;
//import Command.AddWaitingToClientCommand;
//import Commandcom.example.ryanblaser.tickettoride.CommandContainer;
//import Command.DeleteGameCommand;
//import Command.GetCommandsCommand;
//import Command.ICommand;
//import Command.ListJoinableCommand;
//import Command.ListResumableCommand;
//import Command.ListWaitingCommand;
//import Command.LoginCommand;
//import Command.LoginRegisterResponseCommand;
//import Command.LogoutCommand;
//import Command.LogoutResponseCommand;
//import Command.RegisterCommand;
//import Command.StartGameCommand;
<<<<<<< HEAD
=======
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.LoginCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.RegisterCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.StartGameCommand;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static com.example.ryanblaser.tickettoride.Client.ServerProxy SINGLETON = new com.example.ryanblaser.tickettoride.Client.ServerProxy();

    private ServerProxy() {

    }


    @Override
    public CommandContainer login(User user) throws com.example.ryanblaser.tickettoride.Client.IClient.InvalidUsername, com.example.ryanblaser.tickettoride.Client.IClient.InvalidPassword {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LoginCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new LoginCommand(user));

        CommandContainer loginCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, loginCommand);
            clientCommunicator.execute(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginCommand;
    }

    @Override
    public CommandContainer register(String username, String password) throws IClient.UsernameAlreadyExists {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("RegisterCommand");

        List<ICommand> commands = new ArrayList<>();
<<<<<<< HEAD
//        commands.add(new RegisterCommand(username));
=======
        commands.add(new RegisterCommand(new User(username, password)));
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8

        CommandContainer registerCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, registerCommand);
            clientCommunicator.execute(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return registerCommand;
    }


    @Override
    public CommandContainer addResumableGame(int gameId) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("AddResumable");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(new AddResumableToClientCommand(game));
//
////        CommandContainer addGameCommand = new CommandContainer("hello");
//        CommandContainer addGameCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, addGameCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return addGameCommand;
        return null;
    }

    @Override
<<<<<<< HEAD
    public int addJoinableGame() {
=======
    public int addJoinableGame(String str_authentication_code) {
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddJoinableCommand");

        List<ICommand> commands = new ArrayList<>();
<<<<<<< HEAD
        commands.add(new AddJoinableToClientCommand(new Game()));
=======
        commands.add(new AddGameToServerCommand(str_authentication_code));

>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8

        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, addGameCommand);
            ICommand cmd = clientCommunicator.execute(url).get();
//            cmd = new AddJoinableToClientCommand();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public CommandContainer addWaitingGame(int gameId) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("AddWaiting");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(new AddWaitingToClientCommand(game.get_S_gameName()));
//
////        CommandContainer addGameCommand = new CommandContainer("hello");
//        CommandContainer addGameCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, addGameCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return addGameCommand;
        return null;
    }


    @Override
    public CommandContainer removeGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("DeleteGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new DeleteGameCommand(game.get_i_gameId()));

//        CommandContainer removeGameCommand = new CommandContainer("hello");
        CommandContainer removeGameCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, removeGameCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

    @Override
    public CommandContainer startGame(int gameId, String authorizationCode) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("StartGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new StartGameCommand(gameId, authorizationCode));

//        CommandContainer startGameCommand = new CommandContainer("hello");
        CommandContainer startGameCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, startGameCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startGameCommand;
    }

    @Override
    public CommandContainer addPlayer(String authenticationCode, int gameId) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddPlayer");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddPlayerToClientCommand(authenticationCode, gameId));

//        CommandContainer addPlayerCommand = new CommandContainer("hello");
        CommandContainer addPlayerCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, addPlayerCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addPlayerCommand;
    }

    //Added these functions after seeing the Command package
    public CommandContainer logout(String authenticationCode) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LogoutCommand");

        List<ICommand> commands = new ArrayList<>();
//        commands.add(new LogoutCommand(authenticationCode));
//        commands.add(authenticationCode);

        CommandContainer logoutCommand = new CommandContainer(types, commands);

        try {
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, logoutCommand);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logoutCommand;
    }
//
//    public void listJoinableGames(Set<Game> listJoinableGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListJoinable");
//
//        List<ICommand> commands = new ArrayList<>();
////        commands.add(new ListJoinableCommand(listJoinableGames));
//
//        CommandContainer listJoinableCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, listJoinableCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listResumableGames(Set<Game> listResumableGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListResumable");
//
//        List<ICommand> commands = new ArrayList<>();
////        commands.add(new ListResumableCommand(listResumableGames));
//
//        CommandContainer listResumableCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, listResumableCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listWaitingGames(Set<Game> listWaitingGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListWaiting");
//
//        List<ICommand> commands = new ArrayList<>();
////        commands.add(new ListWaitingCommand(listWaitingGames));
//
//        CommandContainer listWaitingCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, listWaitingCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void loginRegisterSucceeded(User user, String authenticationCode) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("LoginRegisterResponse");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(new LoginRegisterResponseCommand(user, authenticationCode));
//
//        CommandContainer loginRegisterResponseCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, loginRegisterResponseCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void logoutSucceeded() {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("LogoutResponse");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(new LogoutResponseCommand());
//
//        CommandContainer logoutResponseCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator_old.SINGLETON.send(urlSuffix, logoutResponseCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public CommandContainer checkForCommands(String username)
    {
        String urlSuffix = "/update";

        List<String> types = new ArrayList<>();
        types.add("GetCommandsCommand");

        List<ICommand> commands = new ArrayList<>();
<<<<<<< Updated upstream
<<<<<<< HEAD
//        commands.add(new GetCommandsCommand());
=======
        commands.add(new GetCommandsCommand());
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
=======
        commands.add(new GetCommandsCommand(username));
>>>>>>> Stashed changes

        CommandContainer checkForCommands = new CommandContainer(types, commands);

        try {
<<<<<<< HEAD
            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
=======
//            URL url = new URL("http://" + LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
<<<<<<< Updated upstream
            URL url = new URL("http://10.0.0.92:8080" + urlSuffix);
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
=======
            URL url = new URL("http://"+ LoginFragment.string_server_address + LoginFragment.string_server_port + urlSuffix);
>>>>>>> Stashed changes
            ClientCommunicator clientCommunicator = new ClientCommunicator(urlSuffix, checkForCommands);
            clientCommunicator.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkForCommands;
    }


	@Override
	public CommandContainer addGame(Game game) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public CommandContainer logout(String str_authentication_code) {
//		// TODO Auto-generated method stub
//		return null;
//	}


//	@Override
//	public CommandContainer logout(String str_authentication_code) {
//		String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("LogoutCommand");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(str_authentication_code);
//
//        CommandContainer logout = new CommandContainer(types, commands);
//
//        try {
//            return ClientCommunicator_old.SINGLETON.send(urlSuffix, logout);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		return null;
//	}
//	
//	public ICommand createGame(String authenticationCode)
//	{
//		String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("AddGameToServerCommand");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(authenticationCode);
//        
//        CommandContainer createGame = new CommandContainer(types, commands);
//
//        try {
//            return ClientCommunicator_old.SINGLETON.send(urlSuffix, createGame);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//	}
//	
//	public ICommand joinGame(String authenticationCode, int gameId)
//	{
//		String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("AddPlayerToServerCommand");
//
//        List<ICommand> commands = new ArrayList<>();
//        commands.add(authenticationCode);
//        commands.add(gameId);
//        
//        CommandContainer joinGame = new CommandContainer(types, commands);
//
//        try {
//            return ClientCommunicator_old.SINGLETON.send(urlSuffix, joinGame);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		return null;
//	}

<<<<<<< HEAD
=======
    public CommandContainer broadcastToChat(String message) {
        return null;
    }
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
}