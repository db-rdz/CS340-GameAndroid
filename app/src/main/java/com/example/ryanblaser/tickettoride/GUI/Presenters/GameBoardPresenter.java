package com.example.ryanblaser.tickettoride.GUI.Presenters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Pair;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel.Scoreboard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.AllRoutes;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.State;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 4/03/17.
 */

public class GameBoardPresenter {

    public static GameBoardPresenter _SINGLETON  = new GameBoardPresenter();
    public static GameBoardFragment _boardFragment = new GameBoardFragment();

    //---------------------VIEW LOGIC VARIABLES--------------//
    private City _firstCityClicked = null;
    private City _secondCityClicked = null;
    private Route _selectedRoute = null;
    private Boolean _clickedOnCityOnce = false;
    private Boolean _clickedOnCityTwice = false;
    private Boolean _furtherActionNeeded = false;
    private List<Route> _selectedRouteList = new ArrayList<>();
    private String _trainCardColor = "";

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

        return AllRoutes.get_allRoutes();
    }

    public String playerUsername() {
        return ClientFacade.SINGLETON.getCurrentUser().getUsername();
    }

    public void initCityPoints(){

    }

    public String get_trainCardColor() {
        return _trainCardColor;
    }

    public void set_trainCardColor(String _trainCardColor) {
        this._trainCardColor = _trainCardColor;
    }

    //-----------------------------------------VIEW LOGIC-----------------------------------------//

    private City getCityByCoodinates(float x, float y){
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
        if(!_clickedOnCityOnce && ClientFacade.SINGLETON.getClientModel().getState().equals(State.YOUR_TURN)){

            _firstCityClicked = selectedCity;
            _clickedOnCityOnce = true;

            return new Pair<>(RESPONSE_STATUS.CITY_CLICKED, "Click a second city adjacent to the first one");

        } //If not second city has been clicked then that means that the selected city is the second city clicked
        else if(!_clickedOnCityTwice && ClientFacade.SINGLETON.getClientModel().getState().equals(State.YOUR_TURN)) {
            _secondCityClicked = selectedCity;
            _clickedOnCityTwice = true;
            //Get the routes that go from city one to city two.
            _selectedRouteList = _firstCityClicked.get_M_Routes().get(_secondCityClicked.get_S_name());

            //If there are more than one routes then further action is required
            if(_selectedRouteList.size() > 1){
                return solveDoubleRoutes();
            }
            else{

                if (_selectedRouteList.get(0).get_S_Color().equals("GRAY")) { //If the route is gray
                    _furtherActionNeeded = true;
                    String toast = "Click the color card you would like to use to claim\nRainbow Cards will be used automatically if needed";

//                    resetViewLogicVariables();
                    return new Pair<>(RESPONSE_STATUS.CLAIM_GRAY_ROUTE, toast);
                }
                else if (canClaimRoute(_selectedRouteList.get(0)))
                {
                    Route selectedRoute = _selectedRouteList.get(0);
                    ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                    resetViewLogicVariables();
                    return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
                }
                else
                {
                    resetViewLogicVariables();
                    return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
                }
            }
        }
        else if(_furtherActionNeeded && ClientFacade.SINGLETON.getClientModel().getState().equals(State.YOUR_TURN)){
            return claimUserChoice(selectedCity);
        }
        return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "Can't claim route right now");
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

            if (_selectedRouteList.get(0).get_S_Color().equals("GRAY")) { //If the route is gray
                _furtherActionNeeded = true;
                String toast = "Click the color card you would like to use to claim\nRainbow Cards will be used automatically if needed";
//                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIM_GRAY_ROUTE, toast);
            }
            else if (canClaimRoute(_selectedRouteList.get(0)))
            {
                Route selectedRoute = _selectedRouteList.get(0);
                ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
            }
            else
            {
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
            }
        }
        else if(_selectedRouteList.get(1).get_Owner() == null){

            if (_selectedRouteList.get(1).get_S_Color().equals("GRAY")) { //If the route is gray
                _furtherActionNeeded = true;
                String toast = "Click the color card you would like to use to claim\nRainbow Cards will be used automatically if needed";
//                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIM_GRAY_ROUTE, toast);
            }
            else if (canClaimRoute(_selectedRouteList.get(1)))
            {
                Route selectedRoute = _selectedRouteList.get(1);
                ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
            }
            else
            {
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
            }
        }
        else{
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.ROUTE_NOT_AVAILABLE,"Routes Already Taken");
        }
    }


    private Pair<RESPONSE_STATUS, String> solveRoutesOfDifferentColor(){
        //Claim Route if there is only one option.
        if(_selectedRouteList.get(0).get_Owner() == null && _selectedRouteList.get(1).get_Owner() != null){
            if (canClaimRoute(_selectedRouteList.get(0)))
            {
                Route selectedRoute = _selectedRouteList.get(0);
                ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
            }
            else
            {
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
            }
        }
        else if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() == null){
            if (canClaimRoute(_selectedRouteList.get(1)))
            {
                Route selectedRoute = _selectedRouteList.get(1);
                ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
            }
            else
            {
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
            }
        }

        //If none of the Routes are available return error message
        if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() != null){
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.ROUTE_NOT_AVAILABLE,"Routes Already Taken");
        }//If both Routes are available then further action is required.
        else{
            _furtherActionNeeded = true;
            if (_selectedRouteList.get(0).get_S_Color().equals("GRAY")) { //If the route is gray
                _furtherActionNeeded = true;
                String toast = "Click the color card you would like to use to claim\nRainbow Cards will be used automatically if needed";
//                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIM_GRAY_ROUTE, toast);
            }
            else {
                String toastText = "Click on " + _firstCityClicked.get_S_name() + " to claim the " +
                        _selectedRouteList.get(0).get_S_Color() + " or click on " + _secondCityClicked.get_S_name() + " to Claim the " +
                        _selectedRouteList.get(1).get_S_Color() + " route";
                return new Pair<>(RESPONSE_STATUS.FURTHER_ACTION_NEEDED, toastText);
            }
        }
    }

    private int getPlayerColor(String owner) {
        int color = -1;
        int gameId = ClientFacade.SINGLETON.getClientModel().getInt_curr_gameId();
        List<String> usernames = ClientFacade.SINGLETON.getClientModel().getGameId_to_usernames().get(gameId);

        int playerIndex = -1;
        for (int i = 0; i < usernames.size(); i++) {
            if (owner.equals(usernames.get(i))) {
                playerIndex = i;
                break;
            }
        }

        if (playerIndex != -1) {
            Scoreboard scoreboard = ClientFacade.SINGLETON.getClientModel().getScoreboards().get(playerIndex);
            color = scoreboard.get_stringToAndroidColor();
        }
        return color;
    }

    public Pair<RESPONSE_STATUS, String> claimRoute(Route route, String claimRouteMessage){

        route.set_Color(getPlayerColor(route.get_Owner()));
        if (AllRoutes.get_RoutesMap().containsKey(route.get_S_MappingName())) { //manually update the route info
            AllRoutes.get_RoutesMap().get(route.get_S_MappingName()).setClaimed(true);
            AllRoutes.get_RoutesMap().get(route.get_S_MappingName()).set_Owner(route.get_Owner());
            AllRoutes.get_RoutesMap().get(route.get_S_MappingName()).set_Color(route.get_Color());
        }
//        AllRoutes.get_RoutesMap().put(route.get_S_MappingName(), route); //replace the current route with the updated one
        Pair<RESPONSE_STATUS, String> pair = new Pair<>(RESPONSE_STATUS.CLAIMED_ROUTE, claimRouteMessage);
        resetViewLogicVariables();
        return pair;
    }

    private Pair<RESPONSE_STATUS, String> claimUserChoice(City c) {

        if(c != _firstCityClicked){
            if (canClaimRoute(_selectedRouteList.get(0)))
            {
                Route selectedRoute = _selectedRouteList.get(0);
                ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
            }
            else
            {
                resetViewLogicVariables();
                return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
            }
        }

        if (canClaimRoute(_selectedRouteList.get(1)))
        {
            Route selectedRoute = _selectedRouteList.get(1);
            ClientFacade.SINGLETON.getClientModel().getState().claimRoute(selectedRoute, _trainCardColor);
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.CLAIMING_ROUTE, "Claiming route from game server");
        }
        else
        {
            resetViewLogicVariables();
            return new Pair<>(RESPONSE_STATUS.CANNOT_CLAIM_ROUTE, "You do not have enough cards to claim this route.");
        }
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

    public void refreshBoard(){
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().setGameBoardFragment(_boardFragment);
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().invalidateBoard();
    }

    //------------------------------------INIT FUNCTIONS------------------------------------------//
    public void set_boardFragment(GameBoardFragment b){
        _boardFragment = b;
    }


    //-------------------------------MODEL ACCESSING FUNCTIONS------------------------------------//

    public void changePlayerState(State state) {
        ClientFacade.SINGLETON.getClientModel().setState(state);
    }

    @NonNull
    public Boolean canClaimRoute(Route routeToClaim) {

        if (!routeToClaim.getClaimed()) {
            PlayerCardHand playerHand = ClientFacade.SINGLETON.getClientModel().getPlayer_hand();
            if (ClientFacade.SINGLETON.getClientModel().getCurrent_player().get_car_count() >= routeToClaim.get_Weight()) {
                switch (routeToClaim.get_S_Color()) {
                    case "GRAY":
                        return selectTrainCardToUseForGrayRoute(routeToClaim, _trainCardColor);

                    case "RED":
                        if (playerHand.getRedCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "RED";
                            return true;
                        }
                        break;
                    case "WHITE":
                        if (playerHand.getWhiteCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "WHITE";
                            return true;
                        }
                        break;
                    case "ORANGE":
                        if (playerHand.getOrangeCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "ORANGE";
                            return true;
                        }
                        break;
                    case "GREEN":
                        if (playerHand.getGreenCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "GREEN";
                            return true;
                        }
                        break;
                    case "BLUE":
                        if (playerHand.getBlueCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "BLUE";
                            return true;
                        }
                        break;
                    case "BLACK":
                        if (playerHand.getBlackCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "BLACK";
                            return true;
                        }
                        break;
                    case "YELLOW":
                        if (playerHand.getYellowCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "YELLOW";
                            return true;
                        }
                        break;
                    case "PINK":
                        if (playerHand.getPinkCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                            _trainCardColor = "PINK";
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    /**
     * Nathan: Checks if a player has enough train cards of the same color to claim a grey route
     * Same implementation as canClaimRoute();
     * @param routeToClaim
     * @param trainCardColor
     * @return
     */
    private Boolean selectTrainCardToUseForGrayRoute(Route routeToClaim, String trainCardColor) {
        PlayerCardHand playerHand = ClientFacade.SINGLETON.getClientModel().getPlayer_hand();
        switch (trainCardColor) {
            case "RED":
                if (playerHand.getRedCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "WHITE":
                if (playerHand.getWhiteCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "ORANGE":
                if (playerHand.getOrangeCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "GREEN":
                if (playerHand.getGreenCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "BLUE":
                if (playerHand.getBlueCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "BLACK":
                if (playerHand.getBlackCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "YELLOW":
                if (playerHand.getYellowCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "PINK":
                if (playerHand.getPinkCardAmount() + playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            case "RAINBOW": //If no colors, then rainbow
                if (playerHand.getRainbowCardAmount() >= routeToClaim.get_Weight()) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }


    public void switchToEndGameView() {
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().switchToEndGameView();

    }
}
