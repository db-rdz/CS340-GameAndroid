package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.PlayerCardHand;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.redbooth.SlidingDeck;

import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_TRAIN;
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
    private int amountOfCardsTaken; //Nathan: Used to check if the player can pick a rainbow card

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
        if (ClientFacade.SINGLETON.getClientModel().getState().equals(YOUR_TURN) ||
                ClientFacade.SINGLETON.getClientModel().getState().equals(PICKING_TRAIN)) {
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
                            PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(item.getId(), true);

                        }
                        else {
                            PlayerActionPresenter._SINGLETON.changePlayerState(PICKING_TRAIN);
                            amountOfCardsTaken++;
                            //TODO: need to refresh the fragment view for the state to take affect on visibility
                            PlayerActionPresenter._SINGLETON.getFaceUpTableTrainCardCommand(item.getId(), false);
                            if (amountOfCardsTaken == 2) { amountOfCardsTaken = 0; }

                        }
                        //TODO: What if the player picks a wild card? How do we end his turn immediately?
                    }
                });
            }
        });

        return view;
    }

}
