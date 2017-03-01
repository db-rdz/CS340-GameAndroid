package com.example.ryanblaser.tickettoride.GUI.Activities;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
=======
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Views.LobbyFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.LoginFragment;
<<<<<<< HEAD
import com.example.ryanblaser.tickettoride.R;
=======
import com.example.ryanblaser.tickettoride.GUI.Views.WaitingFragment;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.User;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8

public class MainActivity extends AppCompatActivity {

    private static LoginFragment loginFragment;
    private static LobbyFragment lobbyFragment;
<<<<<<< HEAD
=======
    private static WaitingFragment waitingFragment;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientFacade.SINGLETON.initilizeClientModel(this);

<<<<<<< HEAD
//        FragmentManager fm = this.getSupportFragmentManager();
//        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
//        if (loginFragment == null) {
//            loginFragment = LoginFragment.newInstance(new User()); //Pass in a User to use inside the LoginFragment
//            if (ClientFacade.SINGLETON.getClientModel().getUser() == null) {
//                fm.beginTransaction().add(R.id.loginFragment, loginFragment).commit();
//
//            }
//        }
//
//
//        lobbyFragment = (LobbyFragment) fm.findFragmentById(R.id.lobbyFragment);
//        if (lobbyFragment == null) {
//            lobbyFragment = LobbyFragment.newInstance();
//
//            if (ClientFacade.SINGLETON.getClientModel().getUser() != null) {
//                fm.beginTransaction().add(R.id.lobbyFragment, lobbyFragment).commit();
//            }
//
//        }

    }

    public void onClick(View v) {

        Intent i;

        i = new Intent(this, BoardActivity.class);
        startActivity(i);
=======
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

        if (waitingFragment == null)
            waitingFragment = WaitingFragment.newInstance("", "");


>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
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

<<<<<<< HEAD
=======
    public static WaitingFragment getWaitingFragment() {
        return waitingFragment;
    }

    public static void setWaitingFragment(WaitingFragment waitingFragment) {
        MainActivity.waitingFragment = waitingFragment;
    }
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
}