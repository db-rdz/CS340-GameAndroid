package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.State;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SliddingAdapter;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SlidingTrainCardAdapter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.redbooth.SlidingDeck;

import java.util.ArrayList;
import java.util.List;

//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_1ST_TRAIN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_2ND_TRAIN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_DEST;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.LAST_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.WAITING_FOR_LAST_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.YOUR_TURN;


public class PlayerActionFragment extends Fragment {

    List<DestCard> copy = new ArrayList<>();

    public SliddingAdapter getSlidingAdapter() {
        return slidingAdapter;
    }

    private  SliddingAdapter slidingAdapter;
    private SlidingTrainCardAdapter trainCardAdapter;

    public PlayerActionFragment() { }

    /**
     * Nathan: Shortened variable to check the player state
     */
//    private State _playerState = PlayerActionPresenter._SINGLETON.get_playerState();

    //-----------------------------VIEW VARIABLES-----------------------------//
    private Button _keepAllCards;
    private Button _getDestCards;
    private SlidingDeck _slidingDeck;
    private SlidingDeck _slidingTrainCards;
    private Button _trainDeck;
    private TextView _turnState;
    private static PlayerActionFragment _playerActionFragment;
    public static final String ARG_PAGE = "page";


    public static PlayerActionFragment newInstance() {
        _playerActionFragment = new PlayerActionFragment();
        return _playerActionFragment;
    }

    public static PlayerActionFragment create(int pageNumber){
        _playerActionFragment = new PlayerActionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        _playerActionFragment.setArguments(args);
        return _playerActionFragment;
    }


    /**
     * Nathan: Closes keyboard on fragment start up.
     * Keyboard pops up instantly because of the EditText for the chat and wouldn't hide
     * when switching fragments
     * @param ctx Context of the fragment
     */
    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerActionPresenter._SINGLETON.initTrainCardMap();
        hideKeyboard(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_action, container, false);

        PlayerActionPresenter._SINGLETON.set_playerActionFragment(this);

        _slidingDeck = (SlidingDeck)v.findViewById(R.id.slidingDeck);
        _slidingTrainCards = (SlidingDeck)v.findViewById(R.id.slidingTrainCards);
        _trainDeck = (Button) v.findViewById(R.id.trainDeck);
        _keepAllCards = (Button) v.findViewById(R.id.keep_allCards);
        _getDestCards = (Button) v.findViewById(R.id.getDestCardsButton);
        _turnState = (TextView) v.findViewById(R.id.textView_turnState);

        if (ClientFacade.SINGLETON.getClientModel().getState().equals(FIRST_TURN)) {
            _turnState.setText("Pick destination cards first");
        }
        else if (ClientFacade.SINGLETON.getClientModel().getState().equals(NOT_YOUR_TURN) ||
                ClientFacade.SINGLETON.getClientModel().getState().equals(WAITING_FOR_LAST_TURN)) {
            _turnState.setText("It's NOT your turn");
        }
        if (ClientFacade.SINGLETON.getClientModel().getState().equals(LAST_TURN)) {
            _turnState.setText("It's your last turn!");
        }

        slidingAdapter  = new SliddingAdapter(getContext());
        trainCardAdapter  = new SlidingTrainCardAdapter(getContext());

        //Loads the 3 destination cards from the PlayerActionPresenter
        slidingAdapter.addAll(PlayerActionPresenter._SINGLETON.get_destCards());
        PlayerActionPresenter._SINGLETON.getCopy().addAll(PlayerActionPresenter._SINGLETON.get_destCards());
        trainCardAdapter.addAll(PlayerActionPresenter._SINGLETON.get_faceUpTrainCards());

        _slidingDeck.setAdapter(slidingAdapter);
        _slidingTrainCards.setAdapter(trainCardAdapter);

        //Nathan: If the player is picking destination cards,
//        if(PlayerActionPresenter._SINGLETON.get_playerState().equals(FIRST_TURN) || PlayerActionPresenter._SINGLETON.get_playerState().equals(YOUR_TURN) ||
//                PlayerActionPresenter._SINGLETON.get_playerState().equals(PICKING_DEST)){
//            _keepAllCards.setVisibility(View.VISIBLE); //He can see the keep all button
//        }
//        else {
//            _keepAllCards.setVisibility(View.GONE); //He cannot see the keep all button
//        }

        //Nathan: If the player isn't on his first turn, and is his turn/picking train cards,
//        if ((PlayerActionPresenter._SINGLETON.get_playerState().equals(PICKING_1ST_TRAIN) || PlayerActionPresenter._SINGLETON.get_playerState().equals(YOUR_TURN)) && !PlayerActionPresenter._SINGLETON.get_playerState().equals(FIRST_TURN)) {
//            _trainDeck.setVisibility(View.VISIBLE); //Can press the deck to get a card
//        }
//        else {
//            _trainDeck.setVisibility(View.INVISIBLE);
//        }

        _getDestCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameBoardPresenter._SINGLETON.set_readyToStart(true);

                ClientFacade.SINGLETON.getClientModel().getState().getDestCards();
            }
        });

        _keepAllCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameBoardPresenter._SINGLETON.set_readyToStart(true);

//                if (_playerState.equals(FIRST_TURN)) {
//                    //Grab destination cards from view
                    List<DestCard> destCardsToKeep = new ArrayList<DestCard>();
                    destCardsToKeep.addAll(PlayerActionPresenter._SINGLETON.get_destCards());
                    PlayerActionPresenter._SINGLETON.get_playerState().keepAllDestCards(destCardsToKeep);
//
//                    PlayerActionPresenter._SINGLETON.firstTurn(destCardsToKeep, "KEEP");
//                    slidingAdapter.notifyDataSetChanged();
//                }

                //PlayerActionPresenter._SINGLETON.set_playerState(NOT_YOUR_TURN);

//                v.setVisibility(View.GONE);
//                container.findViewById(R.id.reject).setVisibility(View.INVISIBLE);
//                slidingAdapter.getRejectButton().setVisibility(View.INVISIBLE);
               // slidingAdapter.notifyDataSetChanged();
            }
        });

        _trainDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SlidingTrainCardAdapter.getAmountOfCardsTaken() < 2) { //Check if player can still draw a card
                    //changePlayerState(); //Change player state first before incrementing

                    //Increase the card drawn amount by 1
                    int count = SlidingTrainCardAdapter.getAmountOfCardsTaken();
                    count++;
                    SlidingTrainCardAdapter.setAmountOfCardsTaken(count);

                    GameBoardPresenter._SINGLETON.set_readyToStart(true);

                    //TODO: refresh fragment to get rid of buttons
                    //TODO: get rid of buttons depending on state
                    PlayerActionPresenter._SINGLETON.get_playerState().getTopDeckTrainCard(count);
                    //Reset the amount of cards drawn back to 0, and change player state
                    if (SlidingTrainCardAdapter.getAmountOfCardsTaken() == 2) {
                        SlidingTrainCardAdapter.setAmountOfCardsTaken(0);
                    }
                }
            }
        });

        ClientFacade.SINGLETON.getClientModel().getBoardActivity().setPlayerActionFragment(this);
        return v;
    }

    public void invalidate(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void refreshPlayerAction() {
        slidingAdapter.addAll(PlayerActionPresenter._SINGLETON.get_destCards());
        trainCardAdapter.addAll(PlayerActionPresenter._SINGLETON.get_faceUpTrainCards());
    }

    public List<DestCard> getCopy() {
        return copy;
    }

    public void setCopy(List<DestCard> copy) {
        this.copy = copy;
    }

    public PlayerActionFragment get_playerActionFragment()
{
    return _playerActionFragment;
}

}
