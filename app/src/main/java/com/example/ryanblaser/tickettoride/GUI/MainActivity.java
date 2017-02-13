package com.example.ryanblaser.tickettoride.GUI;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Put loginFragment here.
        FragmentManager fm = this.getSupportFragmentManager();
        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance(new User()); //Pass in a User to use inside the LoginFragment
            fm.beginTransaction().add(R.id.loginFragment, loginFragment).commit();
        }
    }

    //TODO: What else needs to go in this activity for Phase 1?
}
