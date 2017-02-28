package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * Created by 0joshuaolson1 on 2/15/17.
 */

public interface ILobbyPresenter {
    public void logout();
    public CommandContainer addJoinableGame();
    public List<Integer> getJoinableGames();
    public CommandContainer update() throws IServer.GameIsFullException;
}
