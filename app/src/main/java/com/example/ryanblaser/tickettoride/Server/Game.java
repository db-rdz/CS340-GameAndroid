package com.example.ryanblaser.tickettoride.Server;

import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.List;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class Game {
    private final int MAX_NUM_OF_PLAYERS = 5;
    List<Username> players;
    String _s_game_id;

    public int getMAX_NUM_OF_PLAYERS() {
        return MAX_NUM_OF_PLAYERS;
    }

    public List<Username> getPlayers() {
        return players;
    }

    public void setPlayers(List<Username> players) {
        this.players = players;
    }

    public String get_s_game_id() {
        return _s_game_id;
    }

    public void set_s_game_id(String _s_game_id) {
        this._s_game_id = _s_game_id;
    }
}

