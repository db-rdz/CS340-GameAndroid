package com.example.ryanblaser.tickettoride.Server;
import Client.IClient;
import Command.AddJoinableToClientCommand;
import Command.AddPlayerToClientCommand;
import Command.AddResumableToClientCommand;
import Command.AddWaitingToClientCommand;
import Command.CommandContainer;
import Command.DeleteGameCommand;
import Command.ICommand;
import Command.ListJoinableCommand;
import Command.ListResumableCommand;
import Command.ListWaitingCommand;
import Command.LoginRegisterResponseCommand;
import Command.LogoutResponseCommand;
import Database.DAO;
import GameModels.Game;
import ServerModel.*;
import UserInfo.Username;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * DOESN'T DO ANYTHING IN CLIENT PACKAGE
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {

    public static ServerFacade SINGLETON = new ServerFacade();

    @Override
    public CommandContainer login(String username, String password, String authoritzationCode) throws IClient.InvalidUsername, IClient.InvalidPassword {
        try {
            // tries to retrieve the user from the database
        	if (DAO._SINGLETON.login(username, password))
//            if (DAO._SINGLETON.login(username.getUsername(), username.getPassword()))
            {
                UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
//                UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username.getUsername());

                ICommand success = new LoginRegisterResponseCommand(theUser.get_Username(), theUser.get_Password(), theUser.get_S_token());
//                ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames());
//                ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames());
//                ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames()); 
                
                List<String> types = new ArrayList<>();
                types.add("LoginRegisterResponseCommand");
                types.add("ListJoinableCommand");
                types.add("ListResumableCommand");
                types.add("ListWaitingCommand");

                List<Object> commands = new ArrayList<>();
                commands.add(success);
//                commands.add(listJoinableCommand);
//                commands.add(listResumableCommand);
//                commands.add(listWaitingCommand);

//                CommandContainer response = new CommandContainer("hello");
                CommandContainer response = new CommandContainer(types, commands);

                return response;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//          catch (IClient.InvalidPassword e) { //Complier says this is never reached
//            throw e;
//        } catch (IClient.InvalidUsername e) {
//            throw e;
//        }
    return null;
    }

    @Override
    public CommandContainer register(String username, String password, String authorizationCode) throws IClient.UsernameAlreadyExists {
        try {
//        	if (!DAO._SINGLETON.registerUser(username, password)) {
//                throw new IClient.UsernameAlreadyExists();
//            }

    		UserModel.User theUser = new UserModel.User();
    		theUser.set_Username(username);
    		theUser.set_Password(password);
    		theUser.set_Token(authorizationCode);
//            ServerModel.UserModel.User theUser = DAO._SINGLETON.getUserByUserName(user.getUsername());

            LoginRegisterResponseCommand success = new LoginRegisterResponseCommand(theUser.get_Username(), theUser.get_Password(), theUser.get_Token());

//            ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames()); 
//            ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames()); 
//            ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames()); 

            List<String> types = new ArrayList<>();
            types.add("LoginRegisterResponseCommand");
            types.add("ListJoinableCommand");
            types.add("ListResumableCommand");
            types.add("ListWaitingCommand");

            List<Object> commands = new ArrayList<>();
//            commands.add(success);
            commands.add(theUser.get_Username());
            commands.add(theUser.get_Password());
            commands.add(theUser.get_Token());
            
            List<Game> games = ServerModel.SINGLETON.getAvailableGames();
            List<Integer> gameIds = new ArrayList<>();
            for (int i = 0; i < games.size(); i++)
            {
            	gameIds.add(games.get(i).get_i_gameId());
            }
            commands.add(gameIds);
            
            List<Integer> startedGameIds = new ArrayList<>();
            List<Integer> waitingGameIds = new ArrayList<>();
            games = theUser.getJoinedGames();
            List<Game> startedGames = ServerModel.SINGLETON.getStartedGames();
            Boolean set = false;
            for (int i = 0; i < games.size(); i++)
            {
            	set = false;
            	Game game = games.get(i);
            	for (int j = 0; j < startedGames.size(); j++)
            	{
            		Game startedGame = startedGames.get(j);
            		if (game.get_i_gameId() == startedGame.get_i_gameId())
            		{
            			startedGameIds.add(startedGame.get_i_gameId());
            			set = true;
            		}
               	}
            	if (!set)
            	{
            		waitingGameIds.add(game.get_i_gameId());
            	}
            }
            commands.add(startedGameIds);
            commands.add(waitingGameIds);
//            commands.add(listJoinableCommand);
//            commands.add(listResumableCommand);
//            commands.add(listWaitingCommand);

//            CommandContainer response = new CommandContainer("hello");
          CommandContainer response = new CommandContainer(types, commands);
          return response;

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }


    @Override
    public CommandContainer addGame(Game game) {
        Game.addGame(game, game.get_i_gameId());
        AddJoinableToClientCommand addJoinableGameCommand = new AddJoinableToClientCommand(game.get_i_gameId());

        List<Object> commands = new ArrayList<>();
       // commands.add(addJoinableGameCommand);
        commands.add(game.get_i_gameId());

        List<String> types = new ArrayList<>();
        types.add("AddJoinable");

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        Username username = new Username();

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(UserModel.User.get_L_listOfAllUsers().get(i).get_Username());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, response);
        }
        
        types.clear();
        types.add("AddWaiting");
        
        response.str_type = types;
        
        return response; //TODO: Check if this is right
    }

    @Override
    public CommandContainer removeGame(Game game) {

        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());

        List<Object> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        Username username = new Username();

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(UserModel.User.get_L_listOfAllUsers().get(i).get_Username());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, response);
        } //TODO: Include this in the ServerCommunicator.

        return response;
    }

    @Override
    public CommandContainer startGame(int gameId, String strAuthenticationCode) {
        Game theGame = Game.getGameWithId(gameId);

        //send delete game command to all users not in game

        ICommand deleteGameCommand = new DeleteGameCommand(gameId);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

        List<Object> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

//        CommandContainer commandContainer = new CommandContainer("hello");
      CommandContainer commandContainer = new CommandContainer(types, commands);
        Username username = new Username();

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(UserModel.User.get_L_listOfAllUsers().get(i).get_Username());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

        // send to all users in game
//        Iterator iter = theGame.get_M_idToGame().values().iterator(); //TODO: is this correct??
        Iterator iter = theGame.get_M_idToUserInGame().values().iterator(); //TODO: is this correct??
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(gameId);

        types.clear();
        types.add("AddResumableToClientCommand");

        commands.clear();
        commands.add(addResumableToClientCommand);

//        commandContainer = new CommandContainer("hello");
        commandContainer = new CommandContainer(types, commands);

        while (iter.hasNext()) {
            UserModel.User user = (UserModel.User) iter.next();
            username.setUsername(user.get_Username());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
		return response;
    }

    @Override
    public CommandContainer addPlayer(String strAuthenticationCode, int intGameId) throws GameIsFullException {
        if (!ServerModel.SINGLETON.addPlayerToGame(strAuthenticationCode, intGameId)) { //TODO: Implement
        	Username username = new Username();
        	List<String> types = new ArrayList<>();
            types.add("DeleteGameCommand");
            List<Object> commands = new ArrayList<>();
            commands.add(intGameId);
            CommandContainer commandContainer = new CommandContainer(types, commands);
        	for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
            	username.setUsername(theUser.get_Username());
            	
                if (!Game.isUserInGame(username.getUsername(), intGameId))
                	ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
            }
        	throw new GameIsFullException();
        }
            // not allowed to join
        else {
            // sent to all users
            ICommand addPlayerToClientCommand = new AddPlayerToClientCommand(strAuthenticationCode, intGameId);

            List<String> types = new ArrayList<>();
            types.add("AddPlayerToClientCommand");

            List<Object> commands = new ArrayList<>();
            for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
            	if (theUser.get_Token() == strAuthenticationCode)
                	commands.add(theUser.get_Username());
            }
            commands.add(intGameId);

//            CommandContainer commandContainer = new CommandContainer("hello");
          CommandContainer commandContainer = new CommandContainer(types, commands);
            Username username = new Username();

            for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
            	username.setUsername(theUser.get_Username());
            	
                if (theUser.get_Token() != strAuthenticationCode)
                	ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
            }

            // send to user who joined
            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(intGameId);
//            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(ServerModel.SINGLETON.getGame(intGameId)); //TODO: decide on which Game class to use
            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(intGameId); //TODO: this is correct??

            types.clear();
            types.add("DeleteGameCommand");
            types.add("AddWaiting");

            commands.clear();
            commands.add(intGameId);
            commands.add(intGameId);

//            CommandContainer response = new CommandContainer("hello");
          CommandContainer response = new CommandContainer(types, commands);
            return response;
        }
    }

    @Override
    public CommandContainer addResumableGame(int gameId) {
        //send to user who called function
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(gameId);

        List<String> types = new ArrayList<>();
        types.add("AddResumableToClientCommand");

        List<Object> commands = new ArrayList<>();
        commands.add(addResumableToClientCommand);

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        return response;
    }

    @Override
    public CommandContainer addJoinableGame(int gameId) {
        // send to all other users
        ICommand addJoinableGameCommand = new AddJoinableToClientCommand(gameId);

        List<String> types = new ArrayList<>();
        types.add("AddJoinableToClientCommand");

        List<Object> commands = new ArrayList<>();
        commands.add(addJoinableGameCommand);

//        CommandContainer commandContainer = new CommandContainer("hello");
        CommandContainer commandContainer = new CommandContainer(types, commands);
        Username username = new Username();
        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(UserModel.User.get_L_listOfAllUsers().get(i).get_Username());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }
        return commandContainer;
    }

    @Override
    public CommandContainer addWaitingGame(int gameId) {
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(gameId);
        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(gameId);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");
        types.add("AddWaitingToClientCommand");

        List<Object> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
        commands.add(addWaitingToClientCommand);

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);      
        return response;
    }


    @Override
    public CommandContainer logout(String str_authentication_code) {
        ServerModel.SINGLETON.logOut(str_authentication_code);
        //ICommand logoutCommand = new LogoutResponseCommand();

        List<String> types = new ArrayList<>();
        types.add("LogoutResponseCommand");

        List<Object> commands = new ArrayList<>();

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        return response;
    }
}