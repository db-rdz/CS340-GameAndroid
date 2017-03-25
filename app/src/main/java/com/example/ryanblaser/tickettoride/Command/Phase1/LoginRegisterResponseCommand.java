package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LobbyPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.LoginPresenter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LoginRegisterResponseCommand implements ICommand {
    private User user;
    private Boolean validCredentials;
    private Boolean userLoggedInAlready;
    private Boolean userRegisteredAlready;

    public LoginRegisterResponseCommand(){}
    public LoginRegisterResponseCommand(User user){
        this.user = user;
    }


    @Override
    public User getUser() {
    return user;
    }

    @Override
    public List<ICommand> execute(){

        if (validCredentials && !userLoggedInAlready && userRegisteredAlready) { //login true false true
            ClientFacade.SINGLETON.loginRegisterSucceeded(user);
            LobbyPresenter.SINGLETON.refreshGameLobby();
        }
        else {
            if (userRegisteredAlready) { //register
                LoginPresenter.SINGLETON.showUserRegisteredAlready();
            }
            else if (!validCredentials && !userRegisteredAlready) {
                LoginPresenter.SINGLETON.showBadCredentials();
            }
            else if (userLoggedInAlready && !userRegisteredAlready) {
                LoginPresenter.SINGLETON.showUserLoggedInAlready();
            }
        }
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
        return null;
    }

    public Boolean getValidCredentials() {
        return validCredentials;
    }

    public Boolean getUserLoggedInAlready() {
        return userLoggedInAlready;
    }

    public Boolean getUserRegisteredAlready() {
        return userRegisteredAlready;
    }
}
