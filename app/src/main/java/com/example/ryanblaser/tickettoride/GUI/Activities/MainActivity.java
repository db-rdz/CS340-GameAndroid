package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.GUI.Views.WaitingFragment;
import com.example.ryanblaser.tickettoride.Client.User;

public class MainActivity extends AppCompatActivity {

    private static LoginFragment loginFragment;
    private static LobbyFragment lobbyFragment;
    private static WaitingFragment waitingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientFacade.SINGLETON.initilizeClientModel(this);


        FragmentManager fm = this.getSupportFragmentManager();
        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance(new User()); //Pass in a User to use inside the LoginFragment
            if (ClientFacade.SINGLETON.getClientModel().getUser() == null) {
                fm.beginTransaction().add(R.id.loginFragment, loginFragment).commit();

            }
        }


        lobbyFragment = (LobbyFragment) fm.findFragmentById(R.id.lobbyFragment);
        if (lobbyFragment == null) {
            lobbyFragment = LobbyFragment.newInstance();

            if (ClientFacade.SINGLETON.getClientModel().getUser() != null) {
                fm.beginTransaction().add(R.id.lobbyFragment, lobbyFragment).commit();
            }

        }

        waitingFragment = (WaitingFragment) fm.findFragmentById(R.id.waitingFragment);
        if (waitingFragment == null) {
            waitingFragment = WaitingFragment.newInstance("", "");

            if (ClientFacade.SINGLETON.getClientModel().getWaitingGames().size() > 0) {
                fm.beginTransaction().add(R.id.waitingFragment, waitingFragment).commit();
            }
        }

    }

    public void onClick(View v) {

        Intent i;

        i = new Intent(this, BoardActivity.class);
        startActivity(i);

        FragmentManager fm = this.getSupportFragmentManager();
        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance(new User()); //Pass in a User to use inside the LoginFragment
            if (ClientFacade.SINGLETON.getClientModel().getUser() == null) {
                fm.beginTransaction().add(R.id.loginFragment, loginFragment).commit();

            }
        }


        lobbyFragment = (LobbyFragment) fm.findFragmentById(R.id.lobbyFragment);
        if (lobbyFragment == null) {
            lobbyFragment = LobbyFragment.newInstance();

            if (ClientFacade.SINGLETON.getClientModel().getUser() != null) {
                fm.beginTransaction().add(R.id.lobbyFragment, lobbyFragment).commit();
            }

        }

        waitingFragment = (WaitingFragment) fm.findFragmentById(R.id.waitingFragment);
        if (waitingFragment == null)
            waitingFragment = WaitingFragment.newInstance("", "");

    }

    /**
     * Nathan: Method is called from the InitializeGameCommand and all users will receive this.
     */
    public void switchToGameBoard() {
        Toast.makeText(this, "Starting Game!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        startActivity(intent);
    }


    public static LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public static void setLoginFragment(LoginFragment loginFragment) {
        com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity.loginFragment = loginFragment;
    }

    public static LobbyFragment getLobbyFragment() {
        return lobbyFragment;
    }

    public static void setLobbyFragment(LobbyFragment lobbyFragment) {
        com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity.lobbyFragment = lobbyFragment;
    }

    public void messageInvalidUsername() {
        Toast.makeText(getBaseContext(), "Invalid Username!", Toast.LENGTH_SHORT).show();
    }

    public static WaitingFragment getWaitingFragment() {
        return waitingFragment;
    }

    public static void setWaitingFragment(WaitingFragment waitingFragment) {
        MainActivity.waitingFragment = waitingFragment;
    }
}