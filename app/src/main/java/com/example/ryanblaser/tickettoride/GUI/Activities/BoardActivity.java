package com.example.ryanblaser.tickettoride.GUI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Command.Phase2.EndTurnCommand;
import com.example.ryanblaser.tickettoride.GUI.Adapters.PageAdapter;
import com.example.ryanblaser.tickettoride.GUI.Adapters.SlidingTrainCardAdapter;
import com.example.ryanblaser.tickettoride.GUI.CustomWidgets.CanvasImageView;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.ChatFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayerActionFragment;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayersInfoFragment;
import com.example.ryanblaser.tickettoride.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BoardActivity extends AppCompatActivity {

    private ChatFragment chatFragment;
    private GameBoardFragment gameBoardFragment;
    private PlayersInfoFragment playersInfoFragment;
    private PlayerActionFragment playerActionFragment;

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

        if (chatFragment == null) { chatFragment = ChatFragment.newInstance(); }
        if (gameBoardFragment == null) { gameBoardFragment = GameBoardFragment.newInstance(); }
        if (playersInfoFragment == null) { playersInfoFragment = PlayersInfoFragment.newInstance(); }
        if (playerActionFragment == null)
        {
            playerActionFragment = PlayerActionFragment.newInstance();
            PlayerActionPresenter._SINGLETON.set_playerActionFragment(playerActionFragment);
        }
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

    public void notifyRouteClaimed(final String str_message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getBaseContext(), str_message, Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null) v.setGravity(Gravity.CENTER);
                toast.show();
            }
        });
    }

    public void notifyTurn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "It's your turn!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notifyCardReceived(final String type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "You got a " + type, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notifyPickNewDestCards() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Pick your new Destination Cards", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notifyLastTurn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "It's your last turn!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void notifyUpcomingLastTurn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Last round!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notifyGameOver() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "The game is over!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refreshChat() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (chatFragment.isVisible()) {
                    chatFragment.refreshChat();
                }
            }
        });
    }


    public void refreshGameBoard() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (gameBoardFragment.isVisible()) {
                    gameBoardFragment.refreshBoard();
                }
            }
        });
    }


    public void refreshPlayerAction() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (playerActionFragment.isVisible()) {
                    playerActionFragment.refreshPlayerAction();
                }
            }

        });
    }


    public void refreshPlayerInfo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (playersInfoFragment.isVisible()) {
                    playersInfoFragment.refreshPlayerInfo();
                }
            }
        });
    }


    public void invalidateBoard() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CanvasImageView touchView = (CanvasImageView) gameBoardFragment.getView().findViewById(R.id.fullscreen_content);
                gameBoardFragment.get_blackCardCount().invalidate();
                gameBoardFragment.get_blueCardCount().invalidate();
                gameBoardFragment.get_greenCardCount().invalidate();
                gameBoardFragment.get_orangeCardCount().invalidate();
                gameBoardFragment.get_pinkCardCount().invalidate();
                gameBoardFragment.get_redCardCount().invalidate();
                gameBoardFragment.get_whiteCardCount().invalidate();
                gameBoardFragment.get_yellowCardCount().invalidate();
                gameBoardFragment.get_rainbowCardCount().invalidate();
                touchView.invalidate();

            }
        });
    }

    public void removeFromSliddingApadter(final DestCard destCard) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                playerActionFragment.getSlidingAdapter().remove(destCard);
                playerActionFragment.getSlidingAdapter().notifyDataSetChanged();
            }
        });
    }


    public void switchToEndGameView() {
        Intent intent = new Intent(getBaseContext(), EndGameActivity.class);
        startActivity(intent);
    }

    public ChatFragment getChatFragment() {
        return chatFragment;
    }

    public void setChatFragment(ChatFragment chatFragment) {
        this.chatFragment = chatFragment;
    }

    public GameBoardFragment getGameBoardFragment() {
        return gameBoardFragment;
    }

    public void setGameBoardFragment(GameBoardFragment gameBoardFragment) {
        this.gameBoardFragment = gameBoardFragment;
    }

    public PlayersInfoFragment getPlayersInfoFragment() {
        return playersInfoFragment;
    }

    public void setPlayersInfoFragment(PlayersInfoFragment playersInfoFragment) {
        this.playersInfoFragment = playersInfoFragment;
    }

    public PlayerActionFragment getPlayerActionFragment() {
        return playerActionFragment;
    }

    public void setPlayerActionFragment(PlayerActionFragment playerActionFragment) {
        this.playerActionFragment = playerActionFragment;
    }



}
