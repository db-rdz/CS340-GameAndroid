package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.Client.State;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.redbooth.SlidingDeck;

import static com.example.ryanblaser.tickettoride.Client.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.PICKING_TRAIN_CARD;
import static com.example.ryanblaser.tickettoride.Client.State.YOUR_TURN;

//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_1ST_TRAIN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_2ND_TRAIN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.YOUR_TURN;

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
    private State _playerState = ClientFacade.SINGLETON.getClientModel().getState();

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sliding_traincard_item, parent, false);
        }

        _trainCardImage = (ImageView) view.findViewById(R.id.trainCard);
        _getButton = (Button)view.findViewById(R.id.getTrainCard);

        TrainCard item = getItem(position);
        view.setTag(item);

        int imageResource = PlayerActionPresenter._SINGLETON.convertImageNameToId(item.getType());
        _trainCardImage.setImageResource(imageResource);

        _getButton.setTag(view);
        _getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingDeck slidingDeck = (SlidingDeck) parent;

                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final TrainCard slidingDeckModel = (TrainCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);

                        int index = parent.indexOfChild(item); //The index of the card chosen
                        if (slidingDeckModel.getType().equals("rainbowcard")) {
                            PlayerActionPresenter._SINGLETON.get_playerState().getFaceUpTrainCard(amountOfCardsTaken, index, true);
                        }
                        else
                        {
                            amountOfCardsTaken++;
                            PlayerActionPresenter._SINGLETON.get_playerState().getFaceUpTrainCard(amountOfCardsTaken, index, false);
                        }

                        if (amountOfCardsTaken == 2) {
                            amountOfCardsTaken = 0;
                        }
                    }
                });
            }
        });

        return view;
    }

    private void pickingTrainCard(TrainCard slidingDeckModel, int cardIndex) {
        //Can only pick rainbow cards if on first face up train card draw
        if (slidingDeckModel.getType().equals("rainbowcard") && amountOfCardsTaken == 0) {
            PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(amountOfCardsTaken, cardIndex, true);
        }
        else { //Picking any other cards
            if (slidingDeckModel.getType().equals("rainbowcard")) {
                String message = "Can only pick rainbow card when picking first card\n Please pick another card";
                Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null) v.setGravity(Gravity.CENTER);
                toast.show();
            }
            else {
                amountOfCardsTaken++;
                PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(amountOfCardsTaken, cardIndex, false);
            }
        }
    }

    public static int getAmountOfCardsTaken() {
        return amountOfCardsTaken;
    }

    public static void setAmountOfCardsTaken(int amountOfCardsTaken) {
        SlidingTrainCardAdapter.amountOfCardsTaken = amountOfCardsTaken;
    }
}
