package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.User;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

interface ILoginPresenter {
    public void login(User user);
    public void register(User user);
    public void setCurrentUser(User user);
    public void showLoginMessage();
    public void showSocketTimeoutMessage();
    public void showBadCredentials();
    public void showUserLoggedInAlready();
}
