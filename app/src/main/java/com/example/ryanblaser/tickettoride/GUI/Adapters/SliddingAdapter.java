package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel.iDestCard;
import com.redbooth.SlidingDeck;

/**
 * Created by benjamin on 28/02/17.
 */

public class SliddingAdapter extends ArrayAdapter<iDestCard> {

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
        String originToDestination = item.get_origin() + " " + item.get_destination();
        view.setTag(item);
        ((TextView)view.findViewById(R.id.card_destination)).setText(originToDestination);
        ((TextView)view.findViewById(R.id.card_points)).setText(item.get_points());

        final View completeView = view.findViewById(R.id.reject);

        completeView.setTag(view);

        if(GameBoardPresenter._SINGLETON.is_readyToStart()){
                completeView.setVisibility(View.GONE);
        }



        completeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final iDestCard slidingDeckModel = (iDestCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);
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
