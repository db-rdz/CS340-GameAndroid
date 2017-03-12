package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.PlayerActionFragment;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 7/03/17.
 */

public class PlayerActionPresenter {

    public static PlayerActionPresenter _SINGLETON = new PlayerActionPresenter();

    private PlayerActionFragment _playerActionFragment;
    private Map<String, Integer> _trainCardMap = new HashMap<String, Integer>();



    public PlayerActionPresenter(){

    }

    public void refreshFragment(){

    }

    public int convertImageNameToId(String image){
        return _trainCardMap.get(image);
    }

    public List<TrainCard> getFourTrainCards(){
        List<TrainCard> asdf = new ArrayList<>();
        asdf.add(new TrainCard("red"));
        asdf.add(new TrainCard("black"));
        asdf.add(new TrainCard("rainbow"));
        asdf.add(new TrainCard("green"));
        return asdf;
        //return ActionBoard._SINGLETON.get_fourTrainCards();
    }

    public void initTrainCardMap(){
        _trainCardMap.put("black", R.drawable.blackcard);
        _trainCardMap.put("blue", R.drawable.bluecard);
        _trainCardMap.put("green", R.drawable.greencard);
        _trainCardMap.put("orange", R.drawable.orangecard);
        _trainCardMap.put("pink", R.drawable.pinkcard);
        _trainCardMap.put("rainbow", R.drawable.rainbowcard);
        _trainCardMap.put("red", R.drawable.redcard);
        _trainCardMap.put("white", R.drawable.whitecard);
        _trainCardMap.put("yellow", R.drawable.yellowcard);
    }
}
