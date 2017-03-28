package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_DEST;

/**
 * Created by benjamin on 28/02/17.
 */

public class SliddingAdapter extends ArrayAdapter<iDestCard> {

    private View rejectButton;
    /**
     * Nathan: Shortened variable to check the player state
     */
    private ClientModel.State _playerState = PlayerActionPresenter._SINGLETON.get_playerState();
    private int amountOfCardsTaken = 0; //Nathan: Used to check if the player can pick a dest card still

    public SliddingAdapter(Context context) {
        super(context, R.layout.sliding_item);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sliding_item, parent, false);
        }

        iDestCard item = getItem(position);
        String originToDestination = item.get_origin() + " " + item.get_routeDestination();
        view.setTag(item);
        ((TextView)view.findViewById(R.id.card_destination)).setText(originToDestination);
        ((TextView)view.findViewById(R.id.card_points)).setText(item.get_points());

        rejectButton = view.findViewById(R.id.reject);

        rejectButton.setTag(view);

        //Nathan: If it's the player's first turn or had started to pick destination cards (and picked less than 2),
        if(_playerState.equals(ClientModel.State.YOUR_TURN) || _playerState.equals(FIRST_TURN) ||
            _playerState.equals(PICKING_DEST) ){
            rejectButton.setVisibility(View.VISIBLE); //He can see the reject button
        }
        else {
            rejectButton.setVisibility(View.INVISIBLE); //He cannot see the reject button
        }

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final DestCard slidingDeckModel = (DestCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);
                        View container = (View)item.getParent().getParent();

                        amountOfCardsTaken++;

                        if (_playerState.equals(FIRST_TURN) && amountOfCardsTaken == 1) {
                            //Player must keep at least two destination cards in the first turn
                            container.findViewById(R.id.keep_allCards).setVisibility(View.VISIBLE);
                        }
                        else if (amountOfCardsTaken == 2) {
                            amountOfCardsTaken = 0;
                        }

                        //TODO: refresh fragment view to get rid of buttons
                        //TODO: get rid of buttons depending on state
                        container.findViewById(R.id.keep_allCards).setVisibility(View.VISIBLE); //Makes sure keep all is visible
                        PlayerActionPresenter._SINGLETON.rejectDestCard(slidingDeckModel);
                        remove(slidingDeckModel);
//                        ClientFacade.SINGLETON.getClientModel().getBoardActivity().turnGetTrainCardButtonOff();
                        rejectButton = view;
                        notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
    }

    public View getRejectButton() {
        return rejectButton;
    }
}
