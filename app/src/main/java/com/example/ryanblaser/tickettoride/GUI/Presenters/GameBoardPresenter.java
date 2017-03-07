package com.example.ryanblaser.tickettoride.GUI.Presenters;

import android.graphics.Color;
import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel.testDestinationCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by benjamin on 4/03/17.
 */

public class GameBoardPresenter {
    private int screenSizeX = 0;
    private int screenSizeY = 0;
    private Boolean _readyToStart = false;

    public static GameBoardPresenter _SINGLETON  = new GameBoardPresenter();


    //---------------------------------------GETTERS AND SETTERS----------------------------------//
    public Boolean is_readyToStart() { return _readyToStart; }
    public void set_readyToStart(Boolean _readyToStart) { this._readyToStart = _readyToStart; }

    public List<Route> get_AllRoutes(){

        return Route.get_allRoutes();
    }

    public String get_PlayerUserName(){
        return "Daniel";
    }

    public void initCityPoints(){

    }

    public List<Player> getPlayersInGame(){
        List<Player> asdf = new ArrayList<>();
        asdf.add(new Player("Nathan", 0, 0, Color.MAGENTA,0));
        asdf.add(new Player("Raul", 0, 0, Color.YELLOW,0));
        asdf.add(new Player("Ryan", 0, 0, Color.BLUE,0));
        asdf.add(new Player("Daniel", 0, 0, Color.GREEN,0));
        return asdf;
    }

    public List<testDestinationCard> getFourDestinationCards(){
        List<testDestinationCard> asdf = new ArrayList<>();
        asdf.add(new testDestinationCard("asdf1", "asdf2", "10"));
        asdf.add(new testDestinationCard("asdf3", "asdf4", "10"));
        asdf.add(new testDestinationCard("asdf5", "asdf6", "10"));
        return asdf;
    }

    public Pair<List<String>, HashMap<String, Player>> getInfoForExpandable(){
        List<Player> playerList =  getPlayersInGame();
        List<String> usernameList = new ArrayList<>();
        HashMap<String, Player> info = new HashMap<>();
        for(int i = 0; i < playerList.size(); i++){
            String username = playerList.get(i).get_userName();
            usernameList.add(username);
            info.put(username, playerList.get(i));
        }

        Pair<List<String>, HashMap<String, Player>> adapterInfo =
                new Pair<>(usernameList, info);


        return adapterInfo;
    }


}
