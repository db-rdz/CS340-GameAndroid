package com.example.ryanblaser.tickettoride.Command;
import com.example.ryanblaser.tickettoride.Server.Game;
import com.example.ryanblaser.tickettoride.Server.ServerFacade;
import com.example.ryanblaser.tickettoride.UserInfo.User;

public class StartGameCommand implements ICommand {
    private Game game;
    private int int_authentication_code;
    private StartGameCommand(){}
    public StartGameCommand(Game g, int k){
        game = g;
        int_authentication_code = k;}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void execute(){
        ServerFacade.SINGLETON.startGame(game, int_authentication_code);}
    @Override
    public int getAuthenticationCode(){
        return int_authentication_code;}}
