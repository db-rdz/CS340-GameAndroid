package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.GUI.Views.ILoginView;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.Client.User;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private static ILoginView view;
    public LoginPresenter(ILoginView v){
        view = v;
    }
    public static LoginPresenter SINGLETON = new LoginPresenter(view);


    public void login(User user) {
        try {
            ClientFacade.SINGLETON.login(user);
        } catch (IClient.InvalidPassword invalidPassword) { //Doesn't do anything
            view.showMessage("Bad password!");
        } catch (IClient.InvalidUsername invalidUsername) {
            view.showMessage("Bad username!");
        }
    }

    @Override
    public void register(User user) {
        try {
            ClientFacade.SINGLETON.register(user.getUsername(), user.getPassword());
        } catch (IClient.InvalidPassword invalidPassword) { //Doesn't do anything
            view.showMessage("Bad password!");
        } catch (IClient.InvalidUsername invalidUsername) {
            view.showMessage("Bad username!");
        } catch (IClient.UsernameAlreadyExists usernameAlreadyExists) {
            view.showMessage("This username is taken.");
        }
    }

    public void switchToLobbyView() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().getLoginFragment().switchToLobbyView();

    }

    @Override
    public void setCurrentUser(User user) {
        ClientFacade.SINGLETON.setCurrentUser(user);
    }

    @Override
    public void showLoginMessage() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().showLoginMessage();
    }

    @Override
    public void showBadCredentials() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().showBadCredentials();
    }

    @Override
    public void showUserLoggedInAlready() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().showUserLoggedInAlready();
    }

    public void showUserRegisteredAlready() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().showUserRegisteredAlready();
    }

    @Override
    public void showSocketTimeoutMessage() {
        ClientFacade.SINGLETON.getClientModel().getMainActivity().showSocketTimeoutMessage();
    }
}
