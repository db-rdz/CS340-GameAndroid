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
                public void claimRoute(Route route, String trainCardColor)
                {
                    ClientFacade.SINGLETON.claimRoute(route, trainCardColor);
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
                public void claimRoute(Route route, String trainCardColor)
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

    PICKING_DEST_CARD
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    if (amountOfCardsTaken <= 2) {
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
                    ClientFacade.SINGLETON.keepAllDestCards(keepCards);
                }
                @Override
                public State notifyTurn() {
                    return NOT_YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },

    LAST_TURN_PICKING_DEST_CARD
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your destination cards");
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    if (amountOfCardsTaken <= 2) {
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
                    return LAST_TURN_PICKING_DEST_CARD;
                }
                @Override
                public State endTurn() {
                    ClientFacade.SINGLETON.lastTurnCompleted();
                    return END_GAME;
                }
            },

    PICKING_TRAIN_CARD
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    ClientFacade.SINGLETON.getTopDeckTrainCardCommand(firstSecondCardPick);
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    if (!isWild) { //If the second card picked isn't a rainbow card, get it
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
                    else {
                        PlayerActionPresenter._SINGLETON.makeToast("Can't pick a rainbow as 2nd card\nPick another card");
                    }
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public State notifyTurn() {
                    return NOT_YOUR_TURN;
                }
                @Override
                public State endTurn() {
                    return NOT_YOUR_TURN;
                }
            },

    LAST_TURN_PICKING_TRAIN_CARD
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void getDestCards()
                {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void getTopDeckTrainCard(int firstSecondCardPick)
                {
                    ClientFacade.SINGLETON.getTopDeckTrainCardCommand(firstSecondCardPick);
                }
                @Override
                public void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild)
                {
                    if (!isWild) { //If the second card picked isn't a rainbow card, get it
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
                    else {
                        PlayerActionPresenter._SINGLETON.makeToast("Can't pick a rainbow as 2nd card\nPick another card");
                    }
                }
                @Override
                public void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken) {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public void keepAllDestCards(List<DestCard> keepCards) {
                    PlayerActionPresenter._SINGLETON.makeToast("Pick your second train card");
                }
                @Override
                public State notifyTurn() {
                    return LAST_TURN_PICKING_TRAIN_CARD;
                }
                @Override
                public State endTurn() {
                    ClientFacade.SINGLETON.lastTurnCompleted();
                    return END_GAME;
                }
            },

    FIRST_TURN
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
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

    WAITING_FOR_LAST_TURN
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
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
                    ClientFacade.SINGLETON.getClientModel().getBoardActivity().notifyLastTurn();
                    return LAST_TURN;
                }
                @Override
                public State endTurn() {
                    return LAST_TURN;
                }
            },

    LAST_TURN
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
                {
                    ClientFacade.SINGLETON.claimRoute(route, trainCardColor);
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
                    ClientFacade.SINGLETON.lastTurnCompleted();
                    return END_GAME;
                }
            },
    END_GAME
            {
                @Override
                public void claimRoute(Route route, String trainCardColor)
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
    public abstract void claimRoute(Route route, String trainCardColor);
    public abstract void getDestCards();
    public abstract void getTopDeckTrainCard(int firstSecondCardPick);
    public abstract void getFaceUpTrainCard(int firstSecondCardPick, int index, Boolean isWild);
    public abstract void rejectDestionationCard(DestCard rejectedCard, int amountOfCardsTaken);
    public abstract void keepAllDestCards(List<DestCard> keepCards);

    //Methods server sends back through commands
    public abstract State notifyTurn();
    public abstract State endTurn();
}
