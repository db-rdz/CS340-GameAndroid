package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class StartGameCommand implements ICommand {
    private int gameId;
    private String str_authentication_code;
    private StartGameCommand(){}
    public StartGameCommand(int g, String k){
        gameId = g;
        str_authentication_code = k;}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public CommandContainer execute(){
        return ServerFacade.SINGLETON.startGame(gameId, str_authentication_code);}
    @Override
    public String getAuthenticationCode(){
        return str_authentication_code;}}
