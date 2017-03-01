package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanblaser.tickettoride.GUI.Adapters.SliddingAdapter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel.testDestinationCard;
import com.redbooth.SlidingDeck;


public class PlayerActionFragment extends Fragment {

    public PlayerActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerActionFragment.
     */
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
        SliddingAdapter slidingAdapter  = new SliddingAdapter(getContext());

        slidingAdapter.add(new testDestinationCard("asdf1", "asdf2", "10"));
        slidingAdapter.add(new testDestinationCard("asdf3", "asdf4", "10"));
        slidingAdapter.add(new testDestinationCard("asdf5", "asdf6", "10"));

        slidingDeck.setAdapter(slidingAdapter);

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
