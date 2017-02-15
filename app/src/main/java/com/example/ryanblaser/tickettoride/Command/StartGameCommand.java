package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class StartGameCommand implements ICommand {
    private Game game;
    private String str_authentication_code;
    private StartGameCommand(){}
    public StartGameCommand(Game g, String k){
        game = g;
        str_authentication_code = k;}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public CommandContainer execute(){
        ServerFacade.SINGLETON.startGame(game, str_authentication_code);}
    @Override
    public String getAuthenticationCode(){
        return str_authentication_code;}}
