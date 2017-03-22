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
        ClientFacade.SINGLETON.loginRegisterSucceeded(user);
        LobbyPresenter.SINGLETON.refreshGameLobby();
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
        return null;
    }


}
