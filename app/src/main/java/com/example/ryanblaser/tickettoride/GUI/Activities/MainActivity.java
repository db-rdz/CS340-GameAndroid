package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class MainActivity extends AppCompatActivity {

    private static LoginFragment loginFragment;
    private static LobbyFragment lobbyFragment;

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

    }


    public static LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public static void setLoginFragment(LoginFragment loginFragment) {
        MainActivity.loginFragment = loginFragment;
    }

    public static LobbyFragment getLobbyFragment() {
        return lobbyFragment;
    }

    public static void setLobbyFragment(LobbyFragment lobbyFragment) {
        MainActivity.lobbyFragment = lobbyFragment;
    }

    public void messageInvalidUsername() {
        Toast.makeText(getBaseContext(), "Invalid Username!", Toast.LENGTH_SHORT).show();
    }

}