package com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.CardType;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by benjamin on 4/03/17.
 */

public class PlayerCardHand {

    public PlayerCardHand(){
        _M_typeToCardCount.put("redcard", 0);
        _M_typeToCardCount.put("orangecard", 0);
        _M_typeToCardCount.put("yellowcard", 0);
        _M_typeToCardCount.put("greencard", 0);
        _M_typeToCardCount.put("bluecard", 0);
        _M_typeToCardCount.put("pinkcard", 0);
        _M_typeToCardCount.put("whitecard", 0);
        _M_typeToCardCount.put("blackcard", 0);
        _M_typeToCardCount.put("rainbowcard", 0);
    }

    private Map<String, Integer> _M_typeToCardCount = new HashMap<>();
    private List<DestCard> _L_destCards = new ArrayList<>();

    //--------------------------------------CLASS FUNCTIONS---------------------------------------//
    public void subtractToCardCount(String type){
        int count = _M_typeToCardCount.get(type).intValue();
        count -= 1;
        _M_typeToCardCount.put(type, count); //Replaces the integer value with the new updated card count
    }

    public void addOneToCardCount(String type){
        int count = _M_typeToCardCount.get(type);
        count += 1;
        _M_typeToCardCount.put(type, count); //Replaces the integer value with the new updated card count
    }

    public void initializeHand(List<TrainCard> hand) {
        for (int i = 0; i < hand.size(); i++) {
            addOneToCardCount(hand.get(i).getType());
        }
    }

    /**
     * Nathan: Takes the rejected card, and removes it from the list
     * @param rejectedCards
     */
    public void rejectDestinationCards(List<DestCard> rejectedCards) {
        int size = _L_destCards.size(); //Ensures the for loop will iterate properly
        for (int i = 0; i < rejectedCards.size(); i++) {
            DestCard destCard = rejectedCards.get(i);
            for (int j = 0; j < size; j++) {
                if (_L_destCards.get(i).get_destination().equals(destCard.get_destination())) {
                    _L_destCards.remove(i);
                }
            }

        }

    }

    //-------------------------------------GETTERS AND SETTERS------------------------------------//
    public List<DestCard> get_destCards() { return _L_destCards; }
    public void set_destCards(List<DestCard> destCards) { _L_destCards = destCards; }

    public Map<String, Integer> get_cardCount() { return _M_typeToCardCount; }
    public void set_cardCount(Map<String, Integer> typeToCardCount) { _M_typeToCardCount = typeToCardCount; }

    public int getRedCardAmount(){
        return _M_typeToCardCount.get("redcard");
    }
    public int getOrangeCardAmount(){
        return _M_typeToCardCount.get("orangecard");
    }
    public int getYellowCardAmount(){
        return _M_typeToCardCount.get("yellowcard");
    }
    public int getGreenCardAmount(){
        return _M_typeToCardCount.get("greencard");
    }
    public int getBlueCardAmount(){
        return _M_typeToCardCount.get("bluecard");
    }
    public int getPinkCardAmount(){
        return _M_typeToCardCount.get("pinkcard");
    }
    public int getWhiteCardAmount(){
        return _M_typeToCardCount.get("whitecard");
    }
    public int getBlackCardAmount(){
        return _M_typeToCardCount.get("blackcard");
    }
    public int getRainbowCardAmount(){
        return _M_typeToCardCount.get("rainbowcard");
    }
}
