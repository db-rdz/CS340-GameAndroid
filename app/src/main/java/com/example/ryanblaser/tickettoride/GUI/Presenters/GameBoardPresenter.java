package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;

import java.util.List;

/**
 * Created by benjamin on 4/03/17.
 */

public class GameBoardPresenter {
    private int screenSizeX;
    private int screenSizeY;

    public static GameBoardPresenter _SINGLETON  = new GameBoardPresenter();

    public List<Route> get_AllRoutes(){
        if(!Route.are_RoutesSet()){
            Route.initAllRoutes();
//            City.initAllCities();
//            City.initAdjacentCities();
        }
        return Route.get_allRoutes();
    }

}
