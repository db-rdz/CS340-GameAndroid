package com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.CardType;
import java.util.HashMap;
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

    Map<CardType, Integer> _M_typeToCardCount = new HashMap<>();

    //--------------------------------------CLASS FUNCTIONS---------------------------------------//
    public void subtractToCardCount(CardType type, int subtractNumber){
        int count = _M_typeToCardCount.get(type);
        count -= subtractNumber;
    }

    public void addOneToCardCount(CardType type){
        int count = _M_typeToCardCount.get(type);
        count += 1;
    }
}
