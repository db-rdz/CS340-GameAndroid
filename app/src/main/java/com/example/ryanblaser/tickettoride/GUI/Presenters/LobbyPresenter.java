package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.GUI.Views.ILobbyView;

import java.util.List;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyPresenter implements ILobbyPresenter {

    private static ILobbyView view;
    public LobbyPresenter(ILobbyView v){
        view = v;
//        ClientFacade.SINGLETON.attachLobbyObserver(this);
    }

    public static com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter SINGLETON = new com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter(view);

    @Override
    public void logout(){
        ClientFacade.SINGLETON.logout(ClientFacade.SINGLETON.getClientModel().getStr_authentication_code());
    }

    @Override
    public void addJoinableGame() {
        ClientFacade.SINGLETON.addJoinableGameToServer();
    }

    @Override
    public List<Integer> getJoinableGames() {
        return ClientFacade.SINGLETON.getClientModel().getJoinableGames();
    }

    @Override
    public void addPlayer(int gameId) {
        String authenticationCode = ClientFacade.SINGLETON.getCurrentUser().getStr_authentication_code();
        ClientFacade.SINGLETON.addPlayerToServerModel(authenticationCode, gameId);
    }


    public void switchToWaitingView()
    {
        MainActivity.getLobbyFragment().switchToWaitingView();
    }

    public void refreshGameLobby() {
        MainActivity.getLobbyFragment().refreshGameLobby();
    }

}