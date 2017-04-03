package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.PlayerActionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanBlaser on 3/28/17.
 */

public enum State {
    YOUR_TURN
            {
                @Override
                public void claimRoute(Route route)
                {
                    ClientFacade.SINGLETON.claimRoute(route);
                }
                @Override
                public void getDestCards()
                {
                    ClientFacade.SINGLETON.getDestinationCards();
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    ClientFacade.SINGLETON.getTopDeckTrainCardCommand(firstSecondCardPick);
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    switch (index) {
                        case 0:
                            index = 4;
                            break;
                        case 1:
                            index = 3;
                            break;
                        case 3:
                            index = 1;
                            break;
                        case 4:
                            index = 0;
                            break;
                        default:
                            break;
                    }
                    ClientFacade.SINGLETON.getFaceUpTableTrainCardCommand(firstSecondCardPick, index, isWild);
                }

                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    if (amountOfCardsTaken <= 2) {
                        PlayerActionPresenter._SINGLETON.rejectDestCard(rejectedCard);
                        PlayerActionPresenter._SINGLETON.get_destCards().remove(rejectedCard);
                        PlayerActionPresenter._SINGLETON.get_playerActionFragment().getSlidingAdapter().remove(rejectedCard);
                        PlayerActionPresenter._SINGLETON.get_playerActionFragment().getSlidingAdapter().notifyDataSetChanged();
                    }
                    else
                        //make toast
                        PlayerActionPresenter._SINGLETON.makeToast("You cannot reject any more destination cards!");
                }

                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    ClientFacade.SINGLETON.keepAllDestCards(keepCards);
                }

                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }

                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    NOT_YOUR_TURN
            {
                @Override
                public void claimRoute(Route route)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("It is not your turn!");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    CLAIMING_ROUTE
            {
                @Override
                public void claimRoute(Route route)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot claim another route while claiming a route!");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot get destination cards while claiming a route!");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot get train cards while claiming a route!");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot get train cards while claiming a route!");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot reject a destination cards while claiming a route!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot keep destination cards while claiming a route!");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    PICKING_DEST
            {
                @Override
                public void claimRoute(Route route)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot claim a route while picking destination cards!");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot pick more destination cards!");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot pick train cards!");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot pick train cards!");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot reject any more destination cards!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot keep more destination cards!");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    PICKING_1ST_TRAIN
            {
                @Override
                public void claimRoute(Route route)
                {
                    // make a toast saying you cannot pick a route and train cards in the same turn
                }
                @Override
                public void getDestCards()
                {
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot reject destination cards while picking train cards!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot keep more destination cards!");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    PICKING_2ND_TRAIN
            {
                @Override
                public void claimRoute(Route route)
                {
                    // make a toast saying you cannot pick a route and train cards in the same turn
                }
                @Override
                public void getDestCards()
                {
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot reject destination cards while picking train cards!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("You cannot keep more destination cards!");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    FIRST_TURN
            {
                @Override
                public void claimRoute(Route route)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You must select your destination cards before you can claim a route!");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You must select your destination cards before you can get additional ones!");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You must select your destination cards before you can pick train cards!");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("You must select your destination cards before you can pick train cards!");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    if (amountOfCardsTaken <= 1) {
                        List<DestCard> list = new ArrayList<>();
                        list.add(rejectedCard);
                        PlayerActionPresenter._SINGLETON.firstTurn(list, "REJECT");
                        PlayerActionPresenter._SINGLETON.get_destCards().remove(rejectedCard);
                        PlayerActionPresenter._SINGLETON.get_playerActionFragment().getSlidingAdapter().remove(rejectedCard);
                        PlayerActionPresenter._SINGLETON.get_playerActionFragment().getSlidingAdapter().notifyDataSetChanged();
                    }
                    else
                        PlayerActionPresenter._SINGLETON.makeToast("You cannot reject any more destination cards!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.firstTurn(keepCards, "KEEP");
                }
                @Override
                public State notifyTurn() {
                    return YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },
    LAST_TURN
            {
                @Override
                public void claimRoute(Route route)
                {
                    ClientFacade.SINGLETON.claimRoute(route);
                }
                @Override
                public void getDestCards()
                {
                    ClientFacade.SINGLETON.getDestinationCards();
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    ClientFacade.SINGLETON.getTopDeckTrainCardCommand(firstSecondCardPick);
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    ClientFacade.SINGLETON.getFaceUpTableTrainCardCommand(firstSecondCardPick, index, isWild);
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    if (amountOfCardsTaken <= 1)
                        PlayerActionPresenter._SINGLETON.rejectDestCard(rejectedCard);
                    else
                        //make toast
                        PlayerActionPresenter._SINGLETON.makeToast("You cannot reject any more destination cards!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    ClientFacade.SINGLETON.keepAllDestCards(keepCards);
                }
                @Override
                public State notifyTurn() {
                    return LAST_TURN;
                }
                @Override
                public State endTurn() {
                    return END_GAME;
                }
            },
    END_GAME
            {
                @Override
                public void claimRoute(Route route)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("The game is over!");
                }
                @Override
                public State notifyTurn() {
                    return END_GAME;
                }
                @Override
                public State endTurn() {
                    return END_GAME;
                }
            };

    // Methods presenter calls
    public abstract void claimRoute(Route route);
    public abstract void getDestCards();
    public abstract void getTopDeckTrainCard(int firstSecondCardPick);
    public abstract void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild);
    public abstract void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken);
    public abstract void keepAllDestCards(List<DestCard> keepCards);

    //Methods server sends back through commands
    public abstract State notifyTurn();
    public abstract State endTurn();
}
