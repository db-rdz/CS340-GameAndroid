package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.redbooth.SlidingDeck;

import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_1ST_TRAIN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_2ND_TRAIN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.YOUR_TURN;

/**
 * Created by benjamin on 8/03/17.
 */

public class SlidingTrainCardAdapter extends ArrayAdapter<TrainCard> {

    public SlidingTrainCardAdapter(Context context) {
        super(context, R.layout.sliding_traincard_item);
    }

    private ImageView _trainCardImage;
    private Button _getButton;
    private static int amountOfCardsTaken; //Nathan: Used to check if the player can pick a rainbow card
    /**
     * Nathan: Shortened variable to check the player state
     */
    private ClientModel.State _playerState = ClientFacade.SINGLETON.getClientModel().getState();

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sliding_traincard_item, parent, false);
        }

        _trainCardImage = (ImageView) view.findViewById(R.id.trainCard);
        _getButton = (Button)view.findViewById(R.id.getTrainCard);

        //Nathan: If the player's turn just started or is already picking a card,
        if ((_playerState.equals(YOUR_TURN) || _playerState.equals(PICKING_1ST_TRAIN)) &&
                !_playerState.equals(FIRST_TURN)) {
            _getButton.setVisibility(View.VISIBLE); //Player can see the get button
        }
        else {
            _getButton.setVisibility(View.GONE); //Player can't see the get button
        }

        TrainCard item = getItem(position);
        view.setTag(item);

        int imageResource = PlayerActionPresenter._SINGLETON.convertImageNameToId(item.getType());
        _trainCardImage.setImageResource(imageResource);

        _getButton.setTag(view);
        _getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final TrainCard slidingDeckModel = (TrainCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);

                        //TODO: Prevent player from picking a rainbow card if he picked another card from the face up already.
                        String type = slidingDeckModel.getType();
                        PlayerCardHand playerHand = ClientFacade.SINGLETON.getClientModel().getPlayer_hand();
                        playerHand.addOneToCardCount(type); //Increases total card count to player hand


                        GameBoardPresenter._SINGLETON.refreshCardCounters();
                        remove(slidingDeckModel);

                        //TODO: delete the card from the player model...
                        GameBoardPresenter._SINGLETON.refreshBoard();
                        PlayerInfoPresenter._SINGLETON.refreshPlayerInfo();
                        notifyDataSetChanged();

                        if (slidingDeckModel.getType().equals("rainbowcard") && amountOfCardsTaken == 0) {
                            PlayerActionPresenter._SINGLETON.changePlayerState(NOT_YOUR_TURN);
                            //TODO: need to refresh the fragment view for the state to take affect on visibility
                            PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(amountOfCardsTaken, item.getId(), true);

                        }
                        else {
                            changePlayerState();
                            PlayerActionPresenter._SINGLETON.changePlayerState(PICKING_1ST_TRAIN);
                            amountOfCardsTaken++;
                            //TODO: need to refresh the fragment view for the state to take affect on visibility
                            //TODO: SEND COMMAND TO SERVER

//                            PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(item.getId(), false);
                            if (amountOfCardsTaken == 2) {
                                amountOfCardsTaken = 0;
                                PlayerActionPresenter._SINGLETON.changePlayerState(NOT_YOUR_TURN);
                            }

                        }
                    }
                });
            }
        });

        return view;
    }

    /**
     * Nathan: Determines the state of the player according to how many cards he has draw already
     */
    private void changePlayerState() {
        if (amountOfCardsTaken == 0) {
            _playerState = PICKING_1ST_TRAIN;
        }
        else if (amountOfCardsTaken == 1) {
            _playerState = PICKING_2ND_TRAIN;
        }
    }

    public static int getAmountOfCardsTaken() {
        return amountOfCardsTaken;
    }

    public static void setAmountOfCardsTaken(int amountOfCardsTaken) {
        SlidingTrainCardAdapter.amountOfCardsTaken = amountOfCardsTaken;
    }
}
