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
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SliddingAdapter;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SlidingTrainCardAdapter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

import java.util.Collection;

import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_1ST_TRAIN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_2ND_TRAIN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_DEST;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.YOUR_TURN;


public class PlayerActionFragment extends Fragment {

    public PlayerActionFragment() { }

    /**
     * Nathan: Shortened variable to check the player state
     */
    private ClientModel.State _playerState = PlayerActionPresenter._SINGLETON.get_playerState();

    //-----------------------------VIEW VARIABLES-----------------------------//
    private Button _keepAllCards;
    private SlidingDeck _slidingDeck;
    private SlidingDeck _slidingTrainCards;
    private Button _trainDeck;
    public static final String ARG_PAGE = "page";


    public static PlayerActionFragment newInstance() {
        PlayerActionFragment fragment = new PlayerActionFragment();
        return fragment;
    }

    public static PlayerActionFragment create(int pageNumber){
        PlayerActionFragment fragment = new PlayerActionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerActionPresenter._SINGLETON.initTrainCardMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_action, container, false);

        _slidingDeck = (SlidingDeck)v.findViewById(R.id.slidingDeck);
        _slidingTrainCards = (SlidingDeck)v.findViewById(R.id.slidingTrainCards);
        _trainDeck = (Button) v.findViewById(R.id.trainDeck);
        _keepAllCards = (Button) v.findViewById(R.id.keep_allCards);

        final SliddingAdapter slidingAdapter  = new SliddingAdapter(getContext());
        final SlidingTrainCardAdapter trainCardAdapter  = new SlidingTrainCardAdapter(getContext());

        //Loads the 3 destination cards from the PlayerActionPresenter
        slidingAdapter.addAll(PlayerActionPresenter._SINGLETON.get_destCards());
        trainCardAdapter.addAll(PlayerActionPresenter._SINGLETON.get_faceUpTrainCards());

        _slidingDeck.setAdapter(slidingAdapter);
        _slidingTrainCards.setAdapter(trainCardAdapter);

        //Nathan: If the player is picking destination cards,
        if(_playerState.equals(FIRST_TURN) || _playerState.equals(ClientModel.State.YOUR_TURN) ||
                _playerState.equals(PICKING_DEST)){
            _keepAllCards.setVisibility(View.VISIBLE); //He can see the keep all button
        }
        else {
            _keepAllCards.setVisibility(View.GONE); //He cannot see the keep all button
        }

        //Nathan: If the player isn't on his first turn, and is his turn/picking train cards,
        if ((_playerState.equals(PICKING_1ST_TRAIN) || _playerState.equals(YOUR_TURN)) && !_playerState.equals(FIRST_TURN)) {
            _trainDeck.setVisibility(View.VISIBLE); //Can press the deck to get a card
        }
        else {
            _trainDeck.setVisibility(View.INVISIBLE);
        }

        _keepAllCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameBoardPresenter._SINGLETON.set_readyToStart(true);
                //TODO: add cards to client model

                PlayerActionPresenter._SINGLETON.set_playerState(NOT_YOUR_TURN);

                v.setVisibility(View.GONE);
                View deck = getView().findViewById(R.id.slidingDeck);
                final SliddingAdapter deckAdapter = slidingAdapter;
                slidingAdapter.notifyDataSetChanged();

                //TODO: SEND COMMAND TO SERVER
            }
        });

        _trainDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SlidingTrainCardAdapter.getAmountOfCardsTaken() < 2) { //Check if player can still draw a card
                    changePlayerState(); //Change player state first before incrementing

                    //Increase the card drawn amount by 1
                    int count = SlidingTrainCardAdapter.getAmountOfCardsTaken();
                    count++;
                    SlidingTrainCardAdapter.setAmountOfCardsTaken(count);

                    GameBoardPresenter._SINGLETON.set_readyToStart(true);
                    Toast.makeText(getContext(), "You got a card", Toast.LENGTH_SHORT).show();

                    //TODO: refresh fragment to get rid of buttons
                    //TODO: get rid of buttons depending on state
                    //TODO: SEND COMMAND TO SERVER
                    PlayerActionPresenter._SINGLETON.getTopDeckTrainCardCommand(SlidingTrainCardAdapter.getAmountOfCardsTaken());

                    //Reset the amount of cards drawn back to 0, and change player state
                    if (SlidingTrainCardAdapter.getAmountOfCardsTaken() == 2) {
                        SlidingTrainCardAdapter.setAmountOfCardsTaken(0);
                        PlayerActionPresenter._SINGLETON.set_playerState(NOT_YOUR_TURN);
                    }
                }
            }
        });

        return v;
    }

    /**
     * Nathan: Determines the state of the player according to how many cards he has draw already
     */
    private void changePlayerState() {
        if (SlidingTrainCardAdapter.getAmountOfCardsTaken() == 0) {
            PlayerActionPresenter._SINGLETON.set_playerState(PICKING_1ST_TRAIN);
        }
        else if (SlidingTrainCardAdapter.getAmountOfCardsTaken() == 1) {
            PlayerActionPresenter._SINGLETON.set_playerState(PICKING_2ND_TRAIN);
        }
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

    }
}
