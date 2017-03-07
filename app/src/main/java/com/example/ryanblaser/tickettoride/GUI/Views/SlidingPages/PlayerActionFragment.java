package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryanblaser.tickettoride.GUI.Adapters.SliddingAdapter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

import java.util.Collection;


public class PlayerActionFragment extends Fragment {

    public PlayerActionFragment() {

    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_action, container, false);

        final SlidingDeck slidingDeck = (SlidingDeck)v.findViewById(R.id.slidingDeck);
        final SliddingAdapter slidingAdapter  = new SliddingAdapter(getContext());

        slidingAdapter.addAll((Collection<? extends iDestCard>)
                GameBoardPresenter._SINGLETON.getFourDestinationCards());

        slidingDeck.setAdapter(slidingAdapter);

        Button keepAllCards = (Button) v.findViewById(R.id.keep_allCards);

        if(GameBoardPresenter._SINGLETON.is_readyToStart()){
            keepAllCards.setVisibility(View.GONE);
        }

        keepAllCards.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
