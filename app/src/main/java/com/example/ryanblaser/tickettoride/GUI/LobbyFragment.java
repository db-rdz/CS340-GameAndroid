package com.example.ryanblaser.tickettoride.GUI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.R;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LobbyFragment extends Fragment {

    public LobbyFragment() {
        // Required empty public constructor
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



        return view;
    }
}
