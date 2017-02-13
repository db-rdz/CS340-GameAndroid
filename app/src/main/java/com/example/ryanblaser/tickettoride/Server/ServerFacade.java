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
import com.example.ryanblaser.tickettoride.ServerModel.ServerModel;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {

    public static ServerFacade SINGLETON = new ServerFacade();

    @Override
    public CommandContainer login(User user) throws IClient.InvalidUsername, IClient.InvalidPassword {
        if (!ServerModel.SINGLETON.getUsers().find(user.getUsername())) {
            throw new IClient.InvalidUsername();
        }
        User the_user = ServerModel.getUsers().get(user.getUsername());
        if (user.getPassword() != the_user.getPassword())
        {
            throw new IClient.InvalidPassword();
        }
        int intAuthenticationCode = new Random().nextInt(1000) + 1;

        ICommand success = new LoginRegisterResponseCommand(user, intAuthenticationCode);
        ICommand listJoinableCommand = new ListJoinableCommand(); //TODO: Need to have list in parameter.
        ICommand listResumableCommand = new ListResumableCommand(); //TODO: Need to have list in parameter.
        ICommand listWaitingCommand = new ListWaitingCommand(); //TODO: Need to have list in parameter.

        CommandContainer response = new CommandContainer("LoginRegisterResponseCommand", success);

        return response;
    }

    @Override
    public CommandContainer register(User user) throws IClient.UsernameAlreadyExists {
        if (!ServerModel.SINGLETON.getUsers().find(user.getUsername())) {
            throw new IClient.UsernameAlreadyExists();
        }
        ServerModel.getUsers.add(user);
        int intAuthenticationCode = new Random().nextInt(1000) + 1;

        LoginRegisterResponseCommand success = new LoginRegisterResponseCommand(user, intAuthenticationCode);
        ListJoinableCommand listJoinableCommand = new ListJoinableCommand(); //TODO: Need to have list in parameter.
        ListResumableCommand listResumableCommand = new ListResumableCommand(); //TODO: Need to have list in parameter.
        ListWaitingCommand listWaitingCommand = new ListWaitingCommand(); //TODO: Need to have list in parameter.

        CommandContainer response = new CommandContainer("LoginRegisterResponseCommand", success);

        return response;
    }

    @Override
    public CommandContainer addGame(Game game) {
        ServerModel.SINGLETON.addPlayerToGame(game);
        return null; //TODO: Stub
    }

    @Override
    public CommandContainer removeGame(Game game) {
        ICommand deleteGameCommand = new DeleteGameCommand(game.getId());
        CommandContainer response = new CommandContainer("DeleteGameCommand", deleteGameCommand);
        new ArrayList<ICommand>().add(deleteGameCommand);
        //TODO: Include this in the ServerCommunicator.

        return null;
    }

    @Override
    public CommandContainer startGame(Game game) {
        // send to all users in game
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(); //TODO: Need to have list in parameter.
        //send delete game command to all users not in game
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());

        CommandContainer response = new CommandContainer("AddResumableToClientCommand", addResumableToClientCommand);
        return response;
    }

    @Override
    public CommandContainer addPlayer(Username username, String gameId) throws GameIsFullException {
        if (ServerModel.SINGLETON.getGame(gameId).isFull()) //TODO: Implement
            throw new GameIsFullException();
            // not allowed to join
        else {
            // send to user who joined
            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());
            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);

            // sent to all users
            ICommand addPlayerToClientCommand = new AddPlayerToClientCommand(game);

            CommandContainer response = new CommandContainer("DeleteGameCommand", deleteGameCommand);
            return response;
        }
    }

    @Override
    public CommandContainer addResumableGame(Game game) {
        //send to all other users in the game
        AddResumableToClientCommand addResumableToClientCommand = new AddResumableToClientCommand(game);
        return null; //TODO: stub
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
        // send to all other users
        AddJoinableToClientCommand addJoinableGameCommand = new AddJoinableToClientCommand(game);
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_s_game_id());
        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);

        CommandContainer response = new CommandContainer("addWaitingToClientCommand", addWaitingToClientCommand);
        return response;
    }

    @Override
    public CommandContainer addPlayer(int int_authentication_code, String gameId) {
        ServerModel.SINGLETON.addPlayerToGame(int_authentication_code, gameId);
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(gameId);
        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);

        CommandContainer response = new CommandContainer("addWaitingToClientCommand", addWaitingToClientCommand);
        return response;
    }

    @Override
    public CommandContainer logout(int int_authentication_code) {
        ServerModel.SINGLETON.logout(int_authentication_code);
        ICommand logoutCommand = new LogoutResponseCommand();

        CommandContainer response = new CommandContainer("LogoutCommand", logoutCommand);
        return response;
    }
}