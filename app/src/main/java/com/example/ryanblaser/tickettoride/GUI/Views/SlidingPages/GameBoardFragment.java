package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CityModel.City;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.CustomWidgets.CanvasImageView;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.RESPONSE_STATUS;
import com.example.ryanblaser.tickettoride.R;



public class GameBoardFragment extends Fragment {

    public GameBoardFragment() {
    }


    //----------------------------------FRAGMENT VARIABLES----------------------------------------//

    //-----------------------VIEWS/LAYOUT-------------------//
    private View mContentView;
    private View mControlsView;
    private EditText textMessageView;

    //Card count views
    private TextView _blackCardCount;
    private TextView _blueCardCount;
    private TextView _greenCardCount;
    private TextView _orangeCardCount;
    private TextView _pinkCardCount;
    private TextView _redCardCount;
    private TextView _whiteCardCount;
    private TextView _yellowCardCount;
    private TextView _rainbowCardCount;


    //-----------------------CONFIGURATION VARIABLES--------//

    //BOARD IMAGE CONFIGURATION
    public static final String ARG_PAGE = "page";


    //MENU HIDING CONFIGURATION
    private boolean mVisible;
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    //Some older devices needs a small delay between UI widget updates
    // and a change of the status and navigation bar.
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    //-------------END OF CONFIGURATION VARIABLES------------//




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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_board, container, false);
        mVisible = true;

        mControlsView = v.findViewById(R.id.fullscreen_content_controls);
        mContentView = v.findViewById(R.id.fullscreen_content);
        textMessageView = (EditText) v.findViewById(R.id.text_message);

        setPlayerCardsViews(v);
        setPlayerCardViewValues();

        Button sendMessage = (Button)v.findViewById(R.id.send);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMessage = textMessageView.getText().toString();
                ClientFacade.SINGLETON.getClientModel().getChat().add(textMessage);
                onMsgSent(textMessage);
            }
        });


        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GameBoardPresenter presenter = GameBoardPresenter._SINGLETON;
                    Pair<RESPONSE_STATUS, String> response;
                    response = presenter.resolveClickEvent(event.getX(), event.getY());

                    RESPONSE_STATUS responseCode = response.first;
                    switch(responseCode){
                        case TOGGLE_NEEDED : {
                            toggle();
                            break;
                        }
                        case CITY_CLICKED: {
                            Toast.makeText(getContext(), response.second, Toast.LENGTH_SHORT).show();
                        }
                        case SECOND_CITY_CLICKED : {
                            break;
                        }
                        case CLAIMED_ROUTE : {
                            Toast.makeText(getContext(), response.second, Toast.LENGTH_LONG ).show();
                            invalidateBoard();
                            break;
                        }
                        default: {
                            Toast.makeText(getContext(), response.second, Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }
                return true;
            }
        });
        return v;
    }

    public void setPlayerCardsViews(View v){
        _blackCardCount = (TextView) v.findViewById(R.id.blackCardCount);
        _blueCardCount = (TextView) v.findViewById(R.id.blueCardCount);
        _greenCardCount = (TextView) v.findViewById(R.id.greenCardCount);
        _orangeCardCount = (TextView) v.findViewById(R.id.orangeCardCount);
        _pinkCardCount = (TextView) v.findViewById(R.id.pinkCardCount);
        _redCardCount = (TextView) v.findViewById(R.id.redCardCount);
        _whiteCardCount = (TextView) v.findViewById(R.id.whiteCardCount);
        _yellowCardCount = (TextView) v.findViewById(R.id.yellowCardCount);
        _rainbowCardCount = (TextView) v.findViewById(R.id.rainbowCardCount);
    }

    public void setPlayerCardViewValues(){
//        Player clientPlayer = GameBoardPresenter._SINGLETON.getClientPlayer();
        Player clientPlayer = ClientFacade.SINGLETON.getClientModel().getCurrent_player();
        PlayerCardHand hand = ClientFacade.SINGLETON.getClientModel().getPlayer_hand();

        _blackCardCount.setText(String.valueOf(hand.get_cardCount().get("blackcard")));
        _blueCardCount.setText(String.valueOf(hand.get_cardCount().get("bluecard")));
        _greenCardCount.setText(String.valueOf(hand.get_cardCount().get("greencard")));
        _orangeCardCount.setText(String.valueOf(hand.get_cardCount().get("orangecard")));
        _pinkCardCount.setText(String.valueOf(hand.get_cardCount().get("pinkcard")));
        _redCardCount.setText(String.valueOf(hand.get_cardCount().get("redcard")));
        _whiteCardCount.setText(String.valueOf(hand.get_cardCount().get("whitecard")));
        _yellowCardCount.setText(String.valueOf(hand.get_cardCount().get("yellowcard")));
        _rainbowCardCount.setText(String.valueOf(hand.get_cardCount().get("rainbowcard")));
        _blackCardCount.invalidate();
        _blueCardCount.invalidate();
        _greenCardCount.invalidate();
        _orangeCardCount.invalidate();
        _pinkCardCount.invalidate();
        _redCardCount.invalidate();
        _whiteCardCount.invalidate();
        _yellowCardCount.invalidate();
        _rainbowCardCount.invalidate();
    }

    public void invalidateBoard(){
        CanvasImageView touchView = (CanvasImageView) getView().findViewById(R.id.fullscreen_content);
        _blackCardCount.invalidate();
        _blueCardCount.invalidate();
        _greenCardCount.invalidate();
        _orangeCardCount.invalidate();
        _pinkCardCount.invalidate();
        _redCardCount.invalidate();
        _whiteCardCount.invalidate();
        _yellowCardCount.invalidate();
        _rainbowCardCount.invalidate();
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
            GameBoardPresenter._SINGLETON.getScreenToImageRatio(getContext());
        }
        GameBoardPresenter._SINGLETON.set_boardFragment(this);
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
