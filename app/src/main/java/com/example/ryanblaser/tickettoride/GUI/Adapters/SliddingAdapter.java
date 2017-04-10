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
import com.example.ryanblaser.tickettoride.Client.State;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.NOT_YOUR_TURN;
//import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_DEST;
import static com.example.ryanblaser.tickettoride.Client.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.State.YOUR_TURN;

/**
 * Created by benjamin on 28/02/17.
 */

public class SliddingAdapter extends ArrayAdapter<iDestCard> {

    private View rejectButton;
    /**
     * Nathan: Shortened variable to check the player state
     */
    //private State _playerState = PlayerActionPresenter._SINGLETON.get_playerState();
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
        String originToDestination = item.get_origin() + " -> " + item.get_routeDestination();
        view.setTag(item);
        ((TextView)view.findViewById(R.id.card_destination)).setText(originToDestination);
        ((TextView)view.findViewById(R.id.card_points)).setText(item.get_points());

        rejectButton = view.findViewById(R.id.reject);

        rejectButton.setTag(view);

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final DestCard slidingDeckModel = (DestCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);
                        amountOfCardsTaken++;
                        PlayerActionPresenter._SINGLETON.get_playerState().rejectDestionationCard(slidingDeckModel, amountOfCardsTaken);
                    }
                });
            }
        });

        return view;
    }
}
