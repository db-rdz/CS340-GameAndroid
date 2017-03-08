package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.GUI.Adapters.ExpandableListAdapter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
*   IMPORTANT NOTE:
*   every time you open this fragment you are adding more of the same users to the
*   expandable list adapter.
*
* */
public class PlayersInfoFragment extends Fragment {

    public PlayersInfoFragment() {
        // Required empty public constructor
    }

    private ExpandableListView _expListView;
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
        PlayerInfoPresenter._SINGLETON.set_playersInfoFragment(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_players_info, container, false);
        _expListView = (ExpandableListView) v.findViewById(R.id.lvExp);


        List<String> headers = new ArrayList<>();
        headers.add("Players");

        Pair<List<String>, HashMap<String, Player>> info
                = GameBoardPresenter._SINGLETON.getInfoForExpandable();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getContext(), info.first, info.second);

        _expListView.setAdapter(listAdapter);

        return v;
    }

    public void refresh(){
        _expListView.invalidate();
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
