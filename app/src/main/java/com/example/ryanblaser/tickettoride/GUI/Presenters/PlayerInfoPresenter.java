package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayersInfoFragment;

/**
 * Created by benjamin on 8/03/17.
 */

public class PlayerInfoPresenter {
    public PlayerInfoPresenter(){

    }
    public static PlayerInfoPresenter _SINGLETON = new PlayerInfoPresenter();
    private PlayersInfoFragment _playersInfoFragment;

    public PlayersInfoFragment get_playersInfoFragment() {return _playersInfoFragment; }
    public void set_playersInfoFragment(PlayersInfoFragment _playersInfoFragment) { this._playersInfoFragment = _playersInfoFragment;}

    public void refreshPlayerInfo(){
        _playersInfoFragment.refresh();
    }
}
