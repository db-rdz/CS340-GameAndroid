package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


public class PlayerActionFragment extends Fragment {

    public PlayerActionFragment() { }


    //-----------------------------VIEW VARIABLES-----------------------------//
    private Button _keepAllCards;
    private SlidingDeck _slidingDeck;
    private SlidingDeck _slidingTrainCards;
    public static final String ARG_PAGE = "page";


    public static PlayerActionFragment newInstance(String param1, String param2) {
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

        final SliddingAdapter slidingAdapter  = new SliddingAdapter(getContext());
        final SlidingTrainCardAdapter trainCardAdapter  = new SlidingTrainCardAdapter(getContext());

//        slidingAdapter.addAll((Collection<? extends iDestCard>)
//                GameBoardPresenter._SINGLETON.getThreeDestinationCards());

        //Loads the 3 destination cards from the ClientModel
        slidingAdapter.addAll((Collection<? extends iDestCard>)
                ClientFacade.SINGLETON.getClientModel().getList_dest_cards());

        trainCardAdapter.addAll(PlayerActionPresenter._SINGLETON.get_faceUpTrainCards());

        _slidingDeck.setAdapter(slidingAdapter);
        _slidingTrainCards.setAdapter(trainCardAdapter);

        _keepAllCards = (Button) v.findViewById(R.id.keep_allCards);

        //Nathan: If the player is picking destination cards,
        if(ClientFacade.SINGLETON.getClientModel().getState().equals(ClientModel.State.PICKING_DEST)){
            _keepAllCards.setVisibility(View.VISIBLE); //He can see the keep all button
        }
        else {
            _keepAllCards.setVisibility(View.GONE); //He cannot see the keep all button
        }

        _keepAllCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameBoardPresenter._SINGLETON.set_readyToStart(true);
                v.setVisibility(View.GONE);

                View deck = getView().findViewById(R.id.slidingDeck);

                final SliddingAdapter deckAdapter = slidingAdapter;
                slidingAdapter.notifyDataSetChanged();


            }
        });

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

}
