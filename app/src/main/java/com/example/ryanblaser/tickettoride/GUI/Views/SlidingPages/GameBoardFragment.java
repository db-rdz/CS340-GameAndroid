package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.CustomWidgets.CanvasImageView;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * interface
 * to handle interaction events.
 * Use the {@link GameBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBoardFragment extends Fragment {

    public GameBoardFragment() {
    }


    //----------------------------------FRAGMENT VARIABLES----------------------------------------//

    //-----------------------VIEWS/LAYOUT-------------------//
    private View mContentView;
    private View mControlsView;

    //-----------------------CONFIGURATION VARIABLES--------//

    //BOARD IMAGE CONFIGURATION
    public static final float BOARD_IMAGE_WIDTH = 2030;
    public static final float BOARD_IMAGE_HEIGHT = 1507;
    public static final String ARG_PAGE = "page";
    private float _screenToImageRatioY = 0;
    private float _screenToImageRatioX = 0;

    //MENU HIDING CONFIGURATION
    private boolean mVisible;
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    //Some older devices needs a small delay between UI widget updates
    // and a change of the status and navigation bar.
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    //-------------END OF CONFIGURATION VARIABLES------------//


    //---------------------VIEW LOGIC VARIABLES--------------//
    private City c1 = null;
    private City c2 = null;
    private Route _selectedRoute = null;
    private Boolean _clickedOnCityOnce = false;
    private Boolean _selectedDoubleRoute = false;
    private List<Route> _selectedRouteList = new ArrayList<>();

    //-------------END OF VIEW LOGIC VARIABLES--------------//


    public static GameBoardFragment create(int pageNumber){
        GameBoardFragment fragment = new GameBoardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };


    public static GameBoardFragment newInstance() {
        GameBoardFragment fragment = new GameBoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //TODO: NOT DETECTING CLICKS NOW... FIX PLEASE!!!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_board, container, false);
        mVisible = true;

        mControlsView = v.findViewById(R.id.fullscreen_content_controls);
        mContentView = v.findViewById(R.id.fullscreen_content);
        // Set up the user interaction to manually show or hide the system UI.


        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    GameBoardPresenter._SINGLETON.resolveClickEvent();
                    
                    List<City> cities = City.get_allCities();

                    getScreenToImageRatio();

                    //----------CONVERT THE CLICK COORDINATES TO BOARD COORDINATES------------//
                    float x = _screenToImageRatioX*event.getX();
                    float y = _screenToImageRatioY*event.getY();

                    for(City c : cities){
                        if(c.get_cityPointArea().contains(x, y)){

                            if(!_clickedOnCityOnce){
                                c1 = c;
                                _clickedOnCityOnce = true;
                            }
                            else if(_selectedDoubleRoute){
                                c2 = c;
                                claimUserChoice(c2);
                            }
                            else{
                                c2 = c;
                                _selectedRouteList = c1.get_M_Routes().get(c.get_S_name());
                                if(_selectedRouteList.size() > 1){
                                    solveDoubleRoutes();
                                }
                                else{
                                    Route selectedRoute = _selectedRouteList.get(0);
                                    claimRoute(selectedRoute);
                                }
                            }
                            return true;
                        }
                    }
                    resetViewLogicVariables();
                    toggle();
                }
                return true;
            }
        });

        return v;
    }

    public void solveDoubleRoutes(){
        //IF BOTH ROUTES ARE THE SAME COLOR THEN CHECK IF ONE IS AVAILABLE
        if(_selectedRouteList.get(0).get_Color() == _selectedRouteList.get(1).get_Color()){

            if(_selectedRouteList.get(0).get_Owner() == null) {
                //TODO: Claiming a route must be done in the model and through the presenter
                Route seltectedRoute = _selectedRouteList.get(0);
                claimRoute(seltectedRoute);
            }else if(_selectedRouteList.get(1).get_Owner() == null){
                Route seltectedRoute = _selectedRouteList.get(1);
                claimRoute(seltectedRoute);
            }
            else{
                Toast.makeText(getContext(), "Routes Already Taken", Toast.LENGTH_LONG ).show();
            }
        }//DIFFERENT COLOR LET THE USER CHOOSE
        else if(_selectedRouteList.get(0).get_Owner() == null && _selectedRouteList.get(1).get_Owner() != null){
            claimRoute(_selectedRouteList.get(0));
        }
        else if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() == null){
            claimRoute(_selectedRouteList.get(1));
        }
        else if(_selectedRouteList.get(0).get_Owner() != null && _selectedRouteList.get(1).get_Owner() != null){
            Toast.makeText(getContext(), "Routes Already Taken", Toast.LENGTH_LONG ).show();
            resetViewLogicVariables();
        }
        else{
            _selectedDoubleRoute = true;
            String toastText = "Click on " + c1.get_S_name() + " to claim the " +
                    _selectedRouteList.get(0).get_S_Color() + " or click on " + c2.get_S_name() + " to Claim the " +
                    _selectedRouteList.get(1).get_S_Color() + " route";
            Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG ).show();
        }
    }



    public void claimRoute(Route route){
        if(route.get_Owner() == null){
            //ClaimRoute
            route.set_Owner("Daniel");
            Toast.makeText(getContext(), "You have claimed route " + c1.get_S_name() + "-"
                    + c2.get_S_name(), Toast.LENGTH_LONG ).show();

            invalidateBoard();
            resetViewLogicVariables();

        }
    }

    public void resetViewLogicVariables(){
        _selectedDoubleRoute = false;
        _selectedRoute = null;
        _selectedRouteList = null;
        _clickedOnCityOnce = false;
        c1 = null;
        c2 = null;
    }

    public void claimUserChoice(City c){
        if(c == c1){
            Route selectedRoute = _selectedRouteList.get(0);
            claimRoute(selectedRoute);
        }
        else{
            Route selectedRoute  = _selectedRouteList.get(1);
            claimRoute(selectedRoute);
        }
    }

    public void getScreenToImageRatio(){
        //---------------GET SCREEN SIZE----------------------//
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;

        _screenToImageRatioY = BOARD_IMAGE_HEIGHT/height;
        _screenToImageRatioX = BOARD_IMAGE_WIDTH/width;
    }

    public void invalidateBoard(){
        CanvasImageView touchView = (CanvasImageView) getView().findViewById(R.id.fullscreen_content);
        touchView.invalidate();
    }

    public void onMsgReceived(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG ).show();
    }

    public void onMsgSent(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG ).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(!Route.are_RoutesSet()){
            Route.initAllRoutes();
            City.initAllCities();
            City.initAdjacentCities();
            City.initCityPoints();
        }
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

}
