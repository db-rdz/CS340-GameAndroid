package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ServerProxy;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private ListView listView_players;
    private Button button_start_game, button_refresh;
    private TextView textView_waiting_text;
    private ArrayAdapter<String> list_of_users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ClientFacade.SINGLETON.getClientModel().setGameActivity(this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ticket To Ride - Game Lobby");

        listView_players = (ListView) findViewById(R.id.list_players_in_game);
        textView_waiting_text = (TextView) findViewById(R.id.textView_waiting_text);
        textView_waiting_text.setClickable(false);

        button_start_game = (Button) findViewById(R.id.button_start_game);
        button_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Starting Game!", Toast.LENGTH_SHORT).show();
                //TODO: Add start game functionality
            }
        });

        button_refresh = (Button) findViewById(R.id.button_refresh);
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Refreshed game lobby", Toast.LENGTH_SHORT).show();

                onResume(); //Refreshes the fragment view to show new data.

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!ClientFacade.SINGLETON.getClientModel().getCreatorOfGame()) {
            button_start_game.setClickable(false);
            button_start_game.setVisibility(View.GONE); //Prevents players in the lobby to start the game
            textView_waiting_text.setVisibility(View.VISIBLE);
        }

        List<String> listUsers = new ArrayList<>();

        try {
            int gameId = ClientFacade.SINGLETON.getClientModel().getWaitingGames().get(0);
            listUsers.addAll(ClientFacade.SINGLETON.getClientModel().getGameId_to_usernames().get(gameId));
            if (listUsers.size() > 0) {
                ArrayList<String> userList = new ArrayList<>();
                for (int i = 0; i < listUsers.size(); i++) {
                    userList.add(listUsers.get(i)); //Lists the player
                }
                list_of_users = new ArrayAdapter<String>(getBaseContext(), R.layout.row_info, userList);
                listView_players.setAdapter(list_of_users);
                list_of_users.notifyDataSetChanged();
            }
        } catch (Exception e) {}

    }

}
