package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AddWaitingToClientCommand implements ICommand { // sent after changes from what List... commands sent

    private int gameId;
    private List<String> usernames = new ArrayList<>(); //Server sends back a list of usernames to add to the clientmodel
    private Boolean isCreator;
    //  @JsonProperty("game")
    //  private Game game;
    public AddWaitingToClientCommand(){}
    //  public AddWaitingToClientCommand(Game g){
    //	  game = g;}
    public AddWaitingToClientCommand(int id, List<String> names, Boolean creator) {
        gameId = id;
        usernames = names;
        isCreator = creator;
    }

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
    public List<ICommand> execute(){

    //    for (int i = 1; i <= game.get_numberOfPlayers(); i++) {
    //      ClientFacade.SINGLETON.getClientModel().addPlayerToGameObject(game.getPlayer(i).get_S_username(), game.get_i_gameId());
    //    }
    //
    //    ClientFacade.SINGLETON.addWaitingGame(game.get_i_gameId());
        for (int i = 0; i < usernames.size(); i++) {
            ClientFacade.SINGLETON.getClientModel().addPlayerToGameObject(usernames.get(i), gameId);
        }
        ClientFacade.SINGLETON.addWaitingGame(gameId);
        ClientFacade.SINGLETON.getClientModel().setBoolean_is_creator_of_game(isCreator);
        ClientFacade.SINGLETON.getClientModel().setInt_curr_gameId(gameId);
        return null; //Since client side is all void
    }

    public int getGameId() {
    return gameId;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public Boolean getIsCreator() {
        return isCreator;
    }
}
