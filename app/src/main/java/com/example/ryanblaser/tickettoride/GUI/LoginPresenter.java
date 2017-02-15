package com.example.ryanblaser.tickettoride.GUI;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.UserInfo.User;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public class LoginPresenter implements ILoginPresenter{

    private ILoginView view;
    public LoginPresenter(ILoginView v){
        view = v;
    }
    public void login(User u) {
        try {
            ClientFacade.SINGLETON.login(u);
        } catch (IClient.InvalidPassword invalidPassword) {
            view.showMessage("Bad password!");
        } catch (IClient.InvalidUsername invalidUsername) {
            view.showMessage("Bad username!");
        }
    }
    public void register(User u) {
        try {
            ClientFacade.SINGLETON.register(u);
        } catch (IClient.InvalidPassword invalidPassword) {
            view.showMessage("Bad password!");
        } catch (IClient.InvalidUsername invalidUsername) {
            view.showMessage("Bad username!");
        } catch (IClient.UsernameAlreadyExists usernameAlreadyExists) {
            view.showMessage("This username is taken.");
        }
    }
}
