package com.example.ryanblaser.tickettoride.GUI.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.Poller;
import com.example.ryanblaser.tickettoride.GUI.Activities.GameActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyFragment extends Fragment {

    private Button button_logout, button_new_game, button_refresh;
    private ListView listView_joinable_games;
    private static int game_Id;
    private ArrayAdapter<String> list_of_Games;
    private Poller poller;
    // list views

    public LobbyFragment() {
        ClientFacade.SINGLETON.attachLobbyObserver(this); // does this belong in onCreate?
        game_Id = 0;
    }

    public static com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment newInstance() {
        return new com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lobby, container, false); //Sets the view to grab from the lobby fragment

        listView_joinable_games = (ListView) view.findViewById(R.id.list_joinable);

        //This part links the buttons to the code.
        button_logout = (Button) view.findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Logging out!", Toast.LENGTH_SHORT).show();
                LobbyPresenter.SINGLETON.logout();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        // button_new_game, list views
        button_new_game = (Button) view.findViewById(R.id.button_new_game);
        button_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Created a new game", Toast.LENGTH_SHORT).show();
                LobbyPresenter.SINGLETON.addJoinableGame();
            }
        });

        //TODO: Need to connect the Poller to this funtionality!!
        button_refresh = (Button) view.findViewById(R.id.button_refresh);
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Refreshed game lobby", Toast.LENGTH_SHORT).show();
                onResume(); //Refreshes the fragment view to show new data.

            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Poller poller = new Poller();

//        List<Integer> listJoinableGames = LobbyPresenter.SINGLETON.getJoinableGames();
//        try {
//            LobbyPresenter.SINGLETON.update();
//        } catch (IServer.GameIsFullException e) {
//            e.printStackTrace();
//        }

        List<Integer> listJoinableGames = LobbyPresenter.SINGLETON.getJoinableGames();
        if (listJoinableGames.size() > 0) {
            ArrayList<String> gamesList = new ArrayList<>();
            for (int i = 0; i < listJoinableGames.size(); i++) {
                int inc = listJoinableGames.get(i); //A holder so we don't accidentally increment i
                gamesList.add("Game " + inc); //Lists the game and which game number
            }
            list_of_Games = new ArrayAdapter<String>(getContext(), R.layout.row_info, gamesList);
            listView_joinable_games.setAdapter(list_of_Games);
            listView_joinable_games.setOnItemClickListener(gameItemClickListener);
            list_of_Games.notifyDataSetChanged();
        }
    }

    private AdapterView.OnItemClickListener gameItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String gameSelected = list_of_Games.getItem(i);
            int gameId = Integer.parseInt(gameSelected);
            LobbyPresenter.SINGLETON.addPlayer();
            Intent intent = new Intent(getContext(), GameActivity.class);
            intent.putExtra("GAME", gameSelected);
            startActivity(intent);
        }
    };

    public void switchToWaitingView()
    {
        Intent intent = new Intent(getContext(), GameActivity.class);
        startActivity(intent);

//        MainActivity sudo_mainActivity = ClientFacade.SINGLETON.getClientModel().getMainActivity();
//        FragmentTransaction ft = sudo_mainActivity.getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.lobbyFragment, sudo_mainActivity.getWaitingFragment()); //TODO: lobby doesn't go away
//        ft.commit();
    }
}
