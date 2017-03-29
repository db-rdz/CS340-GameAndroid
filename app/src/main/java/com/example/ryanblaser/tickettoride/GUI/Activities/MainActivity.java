package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.app.backup.BackupDataOutput;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.User;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    private LobbyFragment lobbyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientFacade.SINGLETON.getClientModel().setMainActivity(this);


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

    }

    /**
     * Nathan:
     * Refreshes the lobby when a new game is created on the server
     */
    public void refreshLobbyView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lobbyFragment.refreshGameLobby();
            }
        });
    }

    public void logout() {
        getSupportFragmentManager().beginTransaction().remove(lobbyFragment).commit();
        getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
        FragmentManager fm = this.getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
        loginFragment = LoginFragment.newInstance(new User());
    }


    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment login) {
        loginFragment = login;
    }

    public LobbyFragment getLobbyFragment() {
        return lobbyFragment;
    }

    public void setLobbyFragment(LobbyFragment lobby) {
        lobbyFragment = lobby;
    }

    public void showLoginMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "You're logged in!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showSocketTimeoutMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Server is down", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showBadCredentials() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Username or password is wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showUserLoggedInAlready() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "User is logged in already", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showUserRegisteredAlready() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "User is registered already", Toast.LENGTH_SHORT).show();

            }
        });
    }

}