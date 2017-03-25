package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Adapters.PageAdapter;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SlidingTrainCardAdapter;
import com.example.ryanblaser.tickettoride.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BoardActivity extends AppCompatActivity {



    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PageAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_board);
        ClientFacade.SINGLETON.getClientModel().setBoardActivity(this);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(this.getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);


//        mContentView.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getX() > 0 && event.getX() < 20){
//                    toggle();
//                    return true;
//                }
//                return false;
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    public void turnGetTrainCardButtonOff() {
        findViewById(R.id.getTrainCard).setVisibility(View.INVISIBLE);
    }

    public void turnKeepAllButtonOff() {
        findViewById(R.id.keep_allCards).setVisibility(View.GONE);
    }

    public void turnRejectButtonOff() {
        findViewById(R.id.reject).setVisibility(View.INVISIBLE);
    }

}
