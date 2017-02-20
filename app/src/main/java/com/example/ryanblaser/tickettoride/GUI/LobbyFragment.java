package com.example.ryanblaser.tickettoride.GUI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.R;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyFragment extends Fragment {

    private Button button_logout, button_new_game;
    // list views

    public LobbyFragment() {
        ClientFacade.SINGLETON.attachLobbyObserver(this); // does this belong in onCreate?
    }

    public static LobbyFragment newInstance() {
        return new LobbyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is how Nathan did his OnCreate in Family Map.
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lobby, container, false); //Sets the view to grab from the lobby fragment

        //This part links the buttons to the code.
        button_logout = (Button) view.findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientFacade.SINGLETON.logout();
            }
        });

        // button_new_game, list views

        return view;
    }
}
