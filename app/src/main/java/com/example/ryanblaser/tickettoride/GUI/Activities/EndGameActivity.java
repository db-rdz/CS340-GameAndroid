package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.BoardModel.Scoreboard;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Button returnToLobby = (Button) findViewById(R.id.returnToLobby);
        returnToLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class); //Goes back to list of games
                startActivity(intent);
            }
        });

        List<String> playerResults = new ArrayList<>();
        updatePlayerResult(playerResults);

        ListView scoreboards = (ListView) findViewById(R.id.list_scoreboard);

        ArrayAdapter<String> results = new ArrayAdapter<String>(getBaseContext(), R.layout.row_info, playerResults);
        scoreboards.setAdapter(results);
        results.notifyDataSetChanged();
    }

    private void updatePlayerResult(List<String> playerResults) {
        int gameId = ClientFacade.SINGLETON.getClientModel().getInt_curr_gameId();
        List<String> usernames = ClientFacade.SINGLETON.getClientModel().getGameId_to_usernames().get(gameId);
        List<Scoreboard> scoreboards = ClientFacade.SINGLETON.getClientModel().getScoreboards();

        //dummy data for testing
//        List<String> usernames = new ArrayList<>();
//        usernames.add("Nathan");
//        usernames.add("Daniel");
//        usernames.add("Ryan");
//        usernames.add("Raul");
//        usernames.add("Scott Woodfield");
//        List<Scoreboard> scoreboards = TESTinitScoreboards();

        //Use TreeMap to automatically sort point value from highest to lowest
        TreeMap<Integer, String> highestToLowest = new TreeMap<>(Collections.<Integer>reverseOrder());
        for (int i = 0; i < scoreboards.size(); i++) {
            int points = scoreboards.get(i).getPoints();
            String username = usernames.get(i);

            highestToLowest.put(points, username);
        }

        for (TreeMap.Entry<Integer, String> map : highestToLowest.entrySet()) {
            String info = map.getValue() + ": " + map.getKey();
            playerResults.add(info);
        }
    }

    private List<Scoreboard> TESTinitScoreboards() {
        List<Scoreboard> scoreboards = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.addPoints(10 + (i * 2));
            scoreboards.add(scoreboard);
        }
        return scoreboards;
    }
}
