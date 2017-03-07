package com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.CardType;
import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by benjamin on 4/03/17.
 */

public class PlayerCardHand {

    public PlayerCardHand(){
        _M_typeToCardCount.put(CardType.RED, 0);
        _M_typeToCardCount.put(CardType.WHITE, 0);
        _M_typeToCardCount.put(CardType.ORANGE, 0);
        _M_typeToCardCount.put(CardType.GREEN, 0);
        _M_typeToCardCount.put(CardType.BLUE, 0);
        _M_typeToCardCount.put(CardType.PURPLE, 0);
        _M_typeToCardCount.put(CardType.YELLOW, 0);
        _M_typeToCardCount.put(CardType.PINK, 0);
        _M_typeToCardCount.put(CardType.RAINBOW, 0);
    }

    private Map<CardType, Integer> _M_typeToCardCount = new HashMap<>();
    private List<DestCard> _L_destCards = new ArrayList<>();

    //--------------------------------------CLASS FUNCTIONS---------------------------------------//
    public void subtractToCardCount(CardType type, int subtractNumber){
        int count = _M_typeToCardCount.get(type);
        count -= subtractNumber;
    }

    public void addOneToCardCount(CardType type){
        int count = _M_typeToCardCount.get(type);
        count += 1;
    }

    //-------------------------------------GETTERS AND SETTERS------------------------------------//
    public List<DestCard> get_destCards() { return _L_destCards; }
    public void set_destCards(List<DestCard> destCards) { _L_destCards = destCards; }

    public Map<CardType, Integer> get_cardCount() { return _M_typeToCardCount; }
    public void set_cardCount(Map<CardType, Integer> typeToCardCount) { _M_typeToCardCount = typeToCardCount; }

}
