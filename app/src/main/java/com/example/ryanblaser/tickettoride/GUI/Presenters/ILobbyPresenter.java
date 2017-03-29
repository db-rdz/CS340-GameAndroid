package com.example.ryanblaser.tickettoride.GUI.Presenters;

import java.util.List;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public interface ILobbyPresenter {
    public void logout();
    public void addJoinableGame();
    public List<Integer> getJoinableGames();
    public void addPlayer(int gameId);
}
