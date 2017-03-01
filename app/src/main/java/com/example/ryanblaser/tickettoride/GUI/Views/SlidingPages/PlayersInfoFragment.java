package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanblaser.tickettoride.R;
public class PlayersInfoFragment extends Fragment {

    public PlayersInfoFragment() {
        // Required empty public constructor
    }

    public static final String ARG_PAGE = "page";

    public static PlayersInfoFragment create(int pageNumber){
        PlayersInfoFragment fragment = new PlayersInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
    public static PlayersInfoFragment newInstance(String param1, String param2) {
        PlayersInfoFragment fragment = new PlayersInfoFragment();
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
        return inflater.inflate(R.layout.fragment_players_info, container, false);
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
