package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel.Scoreboard;
import com.example.ryanblaser.tickettoride.GUI.Adapters.DestinationListAdapter;
import com.example.ryanblaser.tickettoride.GUI.Adapters.ExpandableListAdapter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
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

    private final Context context = getContext();
    private ExpandableListView _expListView;
    private ExpandableListView _playerDestCardsView;
    public static final String ARG_PAGE = "page";

    public static PlayersInfoFragment create(int pageNumber){
        PlayersInfoFragment fragment = new PlayersInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
    public static PlayersInfoFragment newInstance() {
        PlayersInfoFragment fragment = new PlayersInfoFragment();
        return fragment;
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
        PlayerInfoPresenter._SINGLETON.set_playersInfoFragment(this);
        hideKeyboard(getContext());
    }


    public void refreshPlayerInfo() {
        Pair<List<String>, List<Scoreboard>> info
                = ClientFacade.SINGLETON.getClientModel().getInfoForExpandable();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getContext(), info.first, info.second);
        _expListView.setAdapter(listAdapter);

        Pair<List<String>, List<Integer>> destCardInfo
                = ClientFacade.SINGLETON.getClientModel().destCardExpandableInfo();
        if (destCardInfo.first.size() > 0 && destCardInfo.second.size() > 0) {
            DestinationListAdapter destinationListAdapter = new DestinationListAdapter(getContext(), destCardInfo.first, destCardInfo.second);
            _playerDestCardsView.setAdapter(destinationListAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_players_info, container, false);
        _expListView = (ExpandableListView) v.findViewById(R.id.lvExp);
        _playerDestCardsView = (ExpandableListView) v.findViewById(R.id.destCardsList);

        List<String> headers = new ArrayList<>();
        headers.add("Players");

        //Nathan: Changed adapter to use the Scoreboard class
        Pair<List<String>, List<Scoreboard>> info
                = ClientFacade.SINGLETON.getClientModel().getInfoForExpandable();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getContext(), info.first, info.second);
        _expListView.setAdapter(listAdapter);

        Pair<List<String>, List<Integer>> destCardInfo
                = ClientFacade.SINGLETON.getClientModel().destCardExpandableInfo();
        if (destCardInfo.first.size() > 0 && destCardInfo.second.size() > 0) {
            DestinationListAdapter destinationListAdapter = new DestinationListAdapter(getContext(), destCardInfo.first, destCardInfo.second);
            _playerDestCardsView.setAdapter(destinationListAdapter);
        }
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
