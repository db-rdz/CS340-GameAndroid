package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class AddJoinableToClientCommand implements ICommand { // sent after changes from what List... commands sent
    private int gameId;
    private Game game;
    public AddJoinableToClientCommand(){}
    public AddJoinableToClientCommand(Game g){
        game = g;}

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
      return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
      return null;
    }

    @Override
    public List<ICommand> execute() {
        ClientFacade.SINGLETON.getClientModel().addJoinableGame(game.get_i_gameId());
        return null;
    }

    @Override
    public Game getGame() {
      return game;
    }
}
