package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayerActionFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayersInfoFragment;

/**
 * Created by benjamin on 28/02/17.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new PlayersInfoFragment();
            case 1: return new GameBoardFragment();
            case 2: return new PlayerActionFragment();
            default: return new GameBoardFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
