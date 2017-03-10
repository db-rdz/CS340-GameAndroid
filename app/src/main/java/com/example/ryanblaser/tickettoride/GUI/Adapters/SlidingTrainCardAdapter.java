package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerInfoPresenter;
import com.example.ryanblaser.tickettoride.R;
import com.redbooth.SlidingDeck;

/**
 * Created by benjamin on 8/03/17.
 */

public class SlidingTrainCardAdapter extends ArrayAdapter<TrainCard> {

    public SlidingTrainCardAdapter(Context context) {
        super(context, R.layout.sliding_traincard_item);
    }

    ImageView _trainCardImage;
    Button _getButton;

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
                final SlidingDeck slidingDeck = (SlidingDeck)parent;
                slidingDeck.swipeItem((View)view.getTag(), new SlidingDeck.SwipeEventListener() {
                    @Override
                    public void onSwipe(SlidingDeck parent, View item) {
                        final TrainCard slidingDeckModel = (TrainCard) item.getTag();
                        GameBoardPresenter._SINGLETON.set_readyToStart(true);
                        int count = GameBoardPresenter._SINGLETON.getClientPlayer().get_Hand().get_cardCount()
                                .get(slidingDeckModel.getType());
                        GameBoardPresenter._SINGLETON.getClientPlayer().get_Hand().get_cardCount()
                                .put(slidingDeckModel.getType(), count + 1);
                        GameBoardPresenter._SINGLETON.refreshCardCounters();
                        remove(slidingDeckModel);

                        //TODO: delete the card from the player model...
                        GameBoardPresenter._SINGLETON.refreshBoard();
                        PlayerInfoPresenter._SINGLETON.refreshPlayerInfo();
                        notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
    }

}
