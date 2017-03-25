package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.FIRST_TURN;
import static com.example.ryanblaser.tickettoride.Client.ClientModel.State.PICKING_DEST;

/**
 * Created by benjamin on 28/02/17.
 */

public class SliddingAdapter extends ArrayAdapter<iDestCard> {

    /**
     * Nathan: Shortened variable to check the player state
     */
    private ClientModel.State _playerState = ClientFacade.SINGLETON.getClientModel().getState();

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

        final View rejectButton = view.findViewById(R.id.reject);

        rejectButton.setTag(view);

        //Nathan: If the player is picking destination cards,
        if(_playerState.equals(FIRST_TURN) || _playerState.equals(ClientModel.State.YOUR_TURN)){
            rejectButton.setVisibility(View.VISIBLE); //He can see the reject button
        }
        else {
            rejectButton.setVisibility(View.GONE); //He cannot see the reject button
        }



        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final iDestCard slidingDeckModel = (iDestCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);

                        PlayerActionPresenter._SINGLETON.changePlayerState(PICKING_DEST);
//                        ClientFacade.SINGLETON.getClientModel().getCurrent_player().updateCurrentDestinationCards(1);
                        //TODO: SEND COMMAND TO SERVER
                        remove(slidingDeckModel);
                        //TODO: delete the card from the player model...
//                        GameBoardPresenter._SINGLETON.

                        View container = (View)item.getParent().getParent();
                        container.findViewById(R.id.keep_allCards).setVisibility(View.GONE);
                        notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
    }

}
