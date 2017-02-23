package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Database.DAO;
import com.example.ryanblaser.tickettoride.Database.DataBase;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.ServerModel.ServerModel;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private ListView listView_players;
    private Button button_start_game;
    private ArrayAdapter<String> list_of_users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ticket To Ride - Game Lobby");

        listView_players = (ListView) findViewById(R.id.list_players_in_game);

        button_start_game = (Button) findViewById(R.id.button_start_game);
        button_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Starting Game!", Toast.LENGTH_SHORT).show();
                //TODO: Add start game functionality
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        List<User> listUsers = new ArrayList<>();
        if (listUsers.size() > 0) {
            ArrayList<String> userList = new ArrayList<>();
            for (int i = 0; i < listUsers.size(); i++) {
                int inc = i; //A holder so we don't accidentally increment i
                userList.add(listUsers.get(i).get_Username()); //Lists the game and which game number
            }
            list_of_users = new ArrayAdapter<String>(getBaseContext(), R.layout.row_info, userList);
            listView_players.setAdapter(list_of_users);
            list_of_users.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
