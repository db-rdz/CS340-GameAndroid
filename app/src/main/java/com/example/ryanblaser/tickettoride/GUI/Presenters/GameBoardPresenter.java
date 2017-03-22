package com.example.ryanblaser.tickettoride.GUI.Presenters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by benjamin on 4/03/17.
 */

public class GameBoardPresenter {

    public static GameBoardPresenter _SINGLETON  = new GameBoardPresenter();
    public static GameBoardFragment _boardFragment = null;

    //---------------------VIEW LOGIC VARIABLES--------------//
    private City _firstCityClicked = null;
    private City _secondCityClicked = null;
    private Route _selectedRoute = null;
    private Boolean _clickedOnCityOnce = false;
    private Boolean _clickedOnCityTwice = false;
    private Boolean _furtherActionNeeded = false;
    private List<Route> _selectedRouteList = new ArrayList<>();

    //---------------------TEST VARIABLES--------------------//
    List<Player> asdf = new ArrayList<>();

    public static final float BOARD_IMAGE_WIDTH = 2030;
    public static final float BOARD_IMAGE_HEIGHT = 1507;
    private float _screenToImageRatioY = 0;
    private float _screenToImageRatioX = 0;

    private Boolean _readyToStart = false;


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

    //-----------------------------------------VIEW LOGIC-----------------------------------------//

    public Pair<RESPONSE_STATUS, String> resolveClickEvent(float x, float y) {
        //----------CONVERT THE CLICK COORDINATES TO BOARD COORDINATES------------//
        x = _screenToImageRatioX * x;
        y = _screenToImageRatioY * y;

        City selectedCity = getCityByCoodinates(x, y);

        //If selected city is null then no city was clicked and thus the cards must be toggled.
        if (selectedCity == null) {
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.TOGGLE_NEEDED, "Toggle Cards");
        }

        //If no city have been clicked before then the selected city is the first city clicked
        if(!_clickedOnCityOnce){

            _firstCityClicked = selectedCity;
            _clickedOnCityOnce = true;

            return new Pair<>(RESPONSE_STATUS.CITY_CLICKED, "Click a second city adjacent to the first one");

        } //If not second city has been clicked then that means that the selected city is the second city clicked
        else if(!_clickedOnCityTwice){
            _secondCityClicked = selectedCity;
            _clickedOnCityTwice = true;
            //Get the routes that go from city one to city two.
            _selectedRouteList = _firstCityClicked.get_M_Routes().get(_secondCityClicked.get_S_name());

            //If there are more than one routes then further action is required
            if(_selectedRouteList.size() > 1){
                return solveDoubleRoutes();
            }
            else{
                Route selectedRoute = _selectedRouteList.get(0);
                return claimRoute(selectedRoute);
            }
        }
        else if(_furtherActionNeeded){
            return claimUserChoice(selectedCity);
        }
        return null;
    }

    private City getCityByCoodinates(float x, float y){
//        List<City> cities = GameBoardPresenter._SINGLETON.getAllCities();
        List<City> cities = City.get_allCities();

        for(City c : cities){
            if(c.get_cityPointArea().contains(x, y)) {
                return c;
            }
        }
        return null;
    }

    private void resetViewLogicVariables(){
        _furtherActionNeeded = false;
        _selectedRoute = null;
        _selectedRouteList = null;
        _clickedOnCityOnce = false;
        _clickedOnCityTwice = false;

        _firstCityClicked = null;
        _secondCityClicked = null;
    }


    private Pair<RESPONSE_STATUS, String> solveDoubleRoutes(){
        //IF BOTH ROUTES ARE THE SAME COLOR THEN CHECK IF ONE IS AVAILABLE
        if(_selectedRouteList.get(0).get_Color() == _selectedRouteList.get(1).get_Color()){
            return solveRoutesOfSameColor();
        }
        return solveRoutesOfDifferentColor();
    }

    private Pair<RESPONSE_STATUS, String> solveRoutesOfSameColor(){
        if(_selectedRouteList.get(0).get_Owner() == null) {
            Route seltectedRoute = _selectedRouteList.get(0);
            return claimRoute(seltectedRoute);
        }else if(_selectedRouteList.get(1).get_Owner() == null){
            Route seltectedRoute = _selectedRouteList.get(1);
            return  claimRoute(seltectedRoute);
        }
        else{
            return new Pair<>(RESPONSE_STATUS.ROUTE_NOT_AVAILABLE,"Routes Already Taken");
        }
    }


    private Pair<RESPONSE_STATUS, String> solveRoutesOfDifferentColor(){
        //Claim Route if there is only one option.
        if(_selectedRouteList.get(0).get_Owner() == null && _selectedRouteList.get(1).get_Owner() != null){
            return claimRoute(_selectedRouteList.get(0));
        }
        else if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() == null){
            return claimRoute(_selectedRouteList.get(1));
        }

        //If none of the Routes are available return error message
        if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() != null){
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.ROUTE_NOT_AVAILABLE,"Routes Already Taken");
        }//If both Routes are available then further action is required.
        else{
            _furtherActionNeeded = true;
            String toastText = "Click on " + _firstCityClicked.get_S_name() + " to claim the " +
                    _selectedRouteList.get(0).get_S_Color() + " or click on " + _secondCityClicked.get_S_name() + " to Claim the " +
                    _selectedRouteList.get(1).get_S_Color() + " route";

            return new Pair<>(RESPONSE_STATUS.FURTHER_ACTION_NEEDED,toastText);
        }
    }

    private Pair<RESPONSE_STATUS, String> claimRoute(Route route){
        if(route.get_Owner() == null){
            route.set_Owner(get_PlayerUserName());
            String toastText =  "You have claimed route " + _firstCityClicked.get_S_name() + "-" +
                    _secondCityClicked.get_S_name();

            ClientFacade.SINGLETON.claimRoute(route); //Sends the route claimed

            String broadcast = route.get_Owner() + " has claimed route " + _firstCityClicked.get_S_name() + "-" +
                    _secondCityClicked.get_S_name();
            ClientFacade.SINGLETON.broadcastToChat(broadcast);

            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.CLAIMED_ROUTE, toastText);
        }

        return new Pair<>(RESPONSE_STATUS.ROUTE_NOT_AVAILABLE,"Route Already Taken");
    }

    private Pair<RESPONSE_STATUS, String> claimUserChoice(City c){

        if(c == _firstCityClicked){
            Route selectedRoute = _selectedRouteList.get(0);
            return claimRoute(selectedRoute);
        }

        Route selectedRoute  = _selectedRouteList.get(1);
        return claimRoute(selectedRoute);
    }

    public void refreshCardCounters(){
        _boardFragment.setPlayerCardViewValues();
    }


    public void getScreenToImageRatio(Context context){
        //---------------GET SCREEN SIZE----------------------//
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;

        _screenToImageRatioY = BOARD_IMAGE_HEIGHT/height;
        _screenToImageRatioX = BOARD_IMAGE_WIDTH/width;
    }


    //------------------------------------VIEW COMMANDS-------------------------------------------//
    public void displayMessage(String message){
        _boardFragment.onMsgReceived(message);
    }

    public void sendMessage(String message){
        ClientFacade.SINGLETON.broadcastToChat(message);
    }

    public void refreshBoard(){
        _boardFragment.invalidateBoard();
    }

    //------------------------------------INIT FUNCTIONS------------------------------------------//
    public void set_boardFragment(GameBoardFragment b){
        _boardFragment = b;
    }


    //-------------------------------MODEL ACCESSING FUNCTIONS------------------------------------//
    public List<Player> getPlayersInGame(){
        asdf = new ArrayList<>();
        asdf.add(new Player("Nathan", 0, 0, Color.RED,0));
        asdf.add(new Player("Raul", 0, 0, Color.YELLOW,0));
        asdf.add(new Player("Ryan", 0, 0, Color.BLUE,0));
        asdf.add(new Player("Daniel", 0, 0, Color.GREEN,0));
        return asdf;
    }

    public Player getClientPlayer(){
        return asdf.get(3);
    }


    public List<City> getAllCities(){
        return City.get_allCities();
    }

//    public List<testDestinationCard> getThreeDestinationCards(){
//        List<testDestinationCard> asdf = new ArrayList<>();
//        asdf.add(new testDestinationCard("asdf1", "asdf2", "10"));
//        asdf.add(new testDestinationCard("asdf3", "asdf4", "10"));
//        asdf.add(new testDestinationCard("asdf5", "asdf6", "10"));
//        return asdf;
//    }

    public void removeDestinationCardFromModel(DestCard card){

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
