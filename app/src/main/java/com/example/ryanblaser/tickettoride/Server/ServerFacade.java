package com.example.ryanblaser.tickettoride.Server;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.ListResumableCommand;
import com.example.ryanblaser.tickettoride.Command.ListWaitingCommand;
import com.example.ryanblaser.tickettoride.Command.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Database.DAO;
import com.example.ryanblaser.tickettoride.ServerModel.ServerModel;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {

    public static ServerFacade SINGLETON = new ServerFacade();

    @Override
    public CommandContainer login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword {
        try {
            // tries to retrieve the user from the database
            if (DAO._SINGLETON.login(user.getUsername(), user.getPassword()))
            {
                com.example.ryanblaser.tickettoride.ServerModel.UserModel.User theUser = DAO._SINGLETON.getUserByUserName(user.getUsername());

                ICommand success = new LoginRegisterResponseCommand(user, theUser.get_S_token());
                ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames()); //TODO: Need to have list in parameter.
                ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames()); //TODO: Need to have list in parameter.
                ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames()); //TODO: Need to have list in parameter.

                List<String> types = new ArrayList<>();
                types.add("LoginRegisterResponseCommand");
                types.add("ListJoinableCommand");
                types.add("ListResumableCommand");
                types.add("ListWaitingCommand");

                List<ICommand> commands = new ArrayList<>();
                commands.add(success);
                commands.add(listJoinableCommand);
                commands.add(listResumableCommand);
                commands.add(listWaitingCommand);

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
    public CommandContainer register(User user) throws IClient.UsernameAlreadyExists {
        try {
            if (!DAO._SINGLETON.registerUser(user.getUsername(), user.getPassword())) {
                throw new IClient.UsernameAlreadyExists();
            }

            com.example.ryanblaser.tickettoride.ServerModel.UserModel.User theUser = DAO._SINGLETON.getUserByUserName(user.getUsername());

            LoginRegisterResponseCommand success = new LoginRegisterResponseCommand(user, theUser.get_S_token());

            ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames()); //TODO: Need to have list in parameter.
            ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames()); //TODO: Need to have list in parameter.
            ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames()); //TODO: Need to have list in parameter.

            List<String> types = new ArrayList<>();
            types.add("LoginRegisterResponseCommand");
            types.add("ListJoinableCommand");
            types.add("ListResumableCommand");
            types.add("ListWaitingCommand");

            List<ICommand> commands = new ArrayList<>();
            commands.add(success);
            commands.add(listJoinableCommand);
            commands.add(listResumableCommand);
            commands.add(listWaitingCommand);

            CommandContainer response = new CommandContainer(types, commands);

            return response;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommandContainer addGame(Game game) {
        com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game.addGame((com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game) game, game.get_s_game_id());
        AddJoinableToClientCommand addJoinableGameCommand = new AddJoinableToClientCommand(game);

        List<ICommand> commands = new ArrayList<>();
        commands.add(addJoinableGameCommand);

        List<String> types = new ArrayList<>();
        types.add("AddJoinableToClientCommand");

        CommandContainer commandContainer = new CommandContainer(types, commands);

        Username username = new Username();

        for (int i = 0; i < com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().get(i).get_S_userName());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }
        return null; //TODO: Stub
    }

    @Override
    public CommandContainer removeGame(Game game) {

        ICommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

        CommandContainer response = new CommandContainer(types, commands);

        Username username = new Username();

        for (int i = 0; i < com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().get(i).get_S_userName());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, response);
        } //TODO: Include this in the ServerCommunicator.

        return response;
    }

//    @Override
    public CommandContainer startGame(Game game) {
        com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game theGame = com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game.getGameWithId(game.get_s_game_id());

        //send delete game command to all users not in game

        ICommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

        CommandContainer commandContainer = new CommandContainer(types, commands);

        Username username = new Username();

        for (int i = 0; i < com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().get(i).get_S_userName());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

        // send to all users in game
        Iterator iter = theGame.get_M_idToGame().values().iterator();
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(game);;

        types.clear();
        types.add("AddResumableToClientCommand");

        commands.clear();
        commands.add(addResumableToClientCommand);

        commandContainer = new CommandContainer(types, commands);

        while (iter.hasNext()) {
            com.example.ryanblaser.tickettoride.ServerModel.UserModel.User user = (com.example.ryanblaser.tickettoride.ServerModel.UserModel.User) iter.next();
            username.setUsername(user.get_S_userName());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

        CommandContainer response = new CommandContainer(types, commands);
        return response;
    }

    @Override
    public CommandContainer addPlayer(int intAuthenticationCode, String sGameId) throws GameIsFullException {
        if (!ServerModel.SINGLETON.addPlayerToGame(intAuthenticationCode, sGameId)) //TODO: Implement
            throw new GameIsFullException();
            // not allowed to join
        else {
            // sent to all users
            ICommand addPlayerToClientCommand = new AddPlayerToClientCommand(intAuthenticationCode, sGameId);

            List<String> types = new ArrayList<>();
            types.add("AddPlayerToClientCommand");

            List<ICommand> commands = new ArrayList<>();
            commands.add(addPlayerToClientCommand);

            CommandContainer commandContainer = new CommandContainer(types, commands);

            Username username = new Username();

            for (int i = 0; i < com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().size(); i++) {
                username.setUsername(com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().get(i).get_S_userName());
                ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
            }

            // send to user who joined
            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(sGameId);
            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(ServerModel.SINGLETON.getGame(sGameId)); //TODO: decide on which Game class to use

            types.clear();
            types.add("DeleteGameCommand");
            types.add("AddWaitingToClientCommand");

            commands.clear();
            commands.add(deleteGameCommand);
            commands.add(addWaitingToClientCommand);

            CommandContainer response = new CommandContainer(types, commands);

            return response;
        }
    }

    @Override
    public CommandContainer addResumableGame(Game game) {
        //send to user who called function
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(game);

        List<String> types = new ArrayList<>();
        types.add("AddResumableToClientCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(addResumableToClientCommand);

        CommandContainer response = new CommandContainer(types, commands);
        return response; //TODO: stub
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
        // send to all other users
        ICommand addJoinableGameCommand = new AddJoinableToClientCommand(game);

        List<String> types = new ArrayList<>();
        types.add("AddJoinableToClientCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(addJoinableGameCommand);

        CommandContainer commandContainer = new CommandContainer(types, commands);
        Username username = new Username();
        for (int i = 0; i < com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username.setUsername(com.example.ryanblaser.tickettoride.ServerModel.UserModel.User.get_L_listOfAllUsers().get(i).get_S_userName());
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }
        return commandContainer;
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());
        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");
        types.add("AddWaitingToClientCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
        commands.add(addWaitingToClientCommand);

        CommandContainer response = new CommandContainer(types, commands);
        return response;
    }


    @Override
    public CommandContainer logout(int int_authentication_code) {
        ServerModel.SINGLETON.logout(int_authentication_code);
        ICommand logoutCommand = new LogoutResponseCommand();

        List<String> types = new ArrayList<>();
        types.add("LogoutCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(logoutCommand);

        CommandContainer response = new CommandContainer(types, commands);
        return response;
    }
}