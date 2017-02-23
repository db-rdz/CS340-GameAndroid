package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ServerProxy;
import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Database.DAO;
import com.example.ryanblaser.tickettoride.GUI.Views.ILobbyView;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.ServerModel;

import java.util.List;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyPresenter implements ILobbyPresenter{

    private static ILobbyView view;
    public LobbyPresenter(ILobbyView v){
        view = v;
//        ClientFacade.SINGLETON.attachLobbyObserver(this);
    }

    public static LobbyPresenter SINGLETON = new LobbyPresenter(view);

    @Override
    public void logout(){
        ClientFacade.SINGLETON.logout(ClientFacade.SINGLETON.getClientModel().getAuthenticationKey());
    }

    @Override
    public CommandContainer addJoinableGame() {
        return ClientFacade.SINGLETON.addJoinableGame();
    }

    @Override
    public List<Integer> getJoinableGames() {
        return ClientFacade.SINGLETON.getClientModel().getJoinableGames();
    }

//    @Override
    public List<Integer> getWaitingGames() {
//        ClientFacade.SINGLETON.getClientModel().setWaitingGames(ServerModel.SINGLETON.getAvailableGames());
        return ClientFacade.SINGLETON.getClientModel().getWaitingGames();
    }

    @Override
    public CommandContainer update() throws IServer.GameIsFullException {
        CommandContainer result = ServerProxy.SINGLETON.checkForCommands();
        ICommand command;
        for (int i = 0; i < result.str_type.size(); i++)
        {
            switch (result.str_type.get(i)) {
                case "AddJoinableCommand" :
                    Number joinableSize = (Number) result.icommand.get(i);
                    command = new AddJoinableToClientCommand(joinableSize.intValue());
                    break;
                case "DeleteGameCommand" :
                    command = (DeleteGameCommand) result.icommand.get(i);
                    break;
                case "AddResumableToClientCommand" :
                    command = (AddResumableToClientCommand) result.icommand.get(i);
                    break;
                case "AddPlayerToClientCommand" :
                    command = (AddPlayerToClientCommand) result.icommand.get(i);
                    break;
                default:
                    command = null;
                    break;
            }
            command.execute();
        }
        return result;
    }
}