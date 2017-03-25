package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.ClientModel;
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
    private List<TrainCard> _faceUpTrainCards = new ArrayList<>();


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
        _trainCardMap.put("blackcard", R.drawable.blackcard);
        _trainCardMap.put("bluecard", R.drawable.bluecard);
        _trainCardMap.put("greencard", R.drawable.greencard);
        _trainCardMap.put("orangecard", R.drawable.orangecard);
        _trainCardMap.put("pinkcard", R.drawable.pinkcard);
        _trainCardMap.put("rainbowcard", R.drawable.rainbowcard);
        _trainCardMap.put("redcard", R.drawable.redcard);
        _trainCardMap.put("whitecard", R.drawable.whitecard);
        _trainCardMap.put("yellowcard", R.drawable.yellowcard);
    }

    public void getFaceUpTableTrainCardCommand(int FirstSecondCardPick, int id, boolean b) {
        ClientFacade.SINGLETON.getFaceUpTableTrainCardCommand(FirstSecondCardPick, id, b);
    }

    public void changePlayerState(ClientModel.State state) {
        ClientFacade.SINGLETON.getClientModel().setState(state);
    }

    public void getTopDeckTrainCardCommand(int FirstSecondCardPick) {
        ClientFacade.SINGLETON.getTopDeckTrainCardCommand(FirstSecondCardPick);
    }
    //----------------------------------GETTERS AND SETTERS--------------------------------------\\
    public Map<String, Integer> get_trainCardMap() {
        return _trainCardMap;
    }
    public void set_trainCardMap(Map<String, Integer> _trainCardMap) {
        this._trainCardMap = _trainCardMap;
    }

    public List<TrainCard> get_faceUpTrainCards() {
        return _faceUpTrainCards;
    }
    public void set_faceUpTrainCards(List<TrainCard> _faceUpTrainCards) {
        this._faceUpTrainCards = _faceUpTrainCards;
    }



}
