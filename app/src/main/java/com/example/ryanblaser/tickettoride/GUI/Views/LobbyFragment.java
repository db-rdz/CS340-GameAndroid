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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.Poller;
import com.example.ryanblaser.tickettoride.GUI.Activities.WaitingActivity;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyFragment extends Fragment {

    private Button button_logout, button_new_game;
    private ListView listView_joinable_games;
    private TextView textView_welcome;
    private static int game_Id;
    private ArrayAdapter<String> list_of_Games;
    private Poller poller;

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

        //Displays a text saying "Welcome *username*!" above the buttons in the lobby
        textView_welcome = (TextView) view.findViewById(R.id.textView_welcome_user);
        textView_welcome.setClickable(false);
        textView_welcome.setText("Welcome " + ClientFacade.SINGLETON.getClientModel().getUser().getUsername() + "!");

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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Integer> listJoinableGames = LobbyPresenter.SINGLETON.getJoinableGames();
        if (listJoinableGames.size() > 0) {
            ArrayList<String> gamesList = new ArrayList<>();
            for (int i = 0; i < listJoinableGames.size(); i++) {
                int gameId = listJoinableGames.get(i); //A holder so we don't accidentally increment i
                gamesList.add("Game " + gameId); //Lists the game and which game number
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
            final String gameSelected = list_of_Games.getItem(i);
            String[] split = gameSelected.split(" ");
            int gameId = Integer.parseInt(split[1]); //gameId is in position 1 of array.
            LobbyPresenter.SINGLETON.addPlayer(gameId);
        }
    };

    public void switchToWaitingView()
    {
        Intent intent = new Intent(getContext(), WaitingActivity.class);
        startActivity(intent);
    }

    /**
     * Nathan:
     * Instead of calling onResume() and causing another view to appear on the android stack,
     * We just simply update the list view the same way we did in onResume()
     */
    public void refreshGameLobby() {
        List<Integer> listJoinableGames = LobbyPresenter.SINGLETON.getJoinableGames();
        if (listJoinableGames.size() > 0) {
            ArrayList<String> gamesList = new ArrayList<>();
            for (int i = 0; i < listJoinableGames.size(); i++) {
                int gameId = listJoinableGames.get(i); //A holder so we don't accidentally increment i
                gamesList.add("Game " + gameId); //Lists the game and which game number
            }
            list_of_Games = new ArrayAdapter<String>(getContext(), R.layout.row_info, gamesList);
            listView_joinable_games.setAdapter(list_of_Games);
            listView_joinable_games.setOnItemClickListener(gameItemClickListener);
            list_of_Games.notifyDataSetChanged();
        }
        else { //show nothing
            List<String> empty = new ArrayList<String>();
            empty.add("There are no available games :(");
            list_of_Games = new ArrayAdapter<String>(getContext(), R.layout.row_info, empty);
            listView_joinable_games.setAdapter(list_of_Games);
//            listView_joinable_games.setOnItemClickListener(gameItemClickListener);
            list_of_Games.notifyDataSetChanged();
        }
    }
}
