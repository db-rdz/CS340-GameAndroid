package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This class will allow one player to grab one train card from the face up train cards on the table.
 * There's another command to grab a second card either from the deck or face up train cards. IF the player
 * doesn't choose a wild card first.
 *
 * If isWild is true, then the player's turn must end immediately.
 * If isWild is false, then the player can still pick another card afterwards
 *
 * Created by natha on 2/27/2017.
 */

public class GetFaceUpTableTrainCardCommand implements ICommand {
    //Data member
    private int _i_gameId;
    private String authenticationCode;
    private int FirstSecondCardPick;

    //Ryan: changed TrainCard to cardIndex to match the model
    private int _i_cardIndex;
    private Boolean isWild; //Is the traincard a wild or normal card?

    //Constructors
    public GetFaceUpTableTrainCardCommand(){}
    public GetFaceUpTableTrainCardCommand(int g, String code, int FirstSecondCardPick, int index, Boolean wild) {
        _i_cardIndex = index;
        authenticationCode = code;
        this.FirstSecondCardPick = FirstSecondCardPick;
        isWild = wild;
        _i_gameId = g;
    }
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public int getCardIndex() {
        return _i_cardIndex;
    }

    public Boolean getWild() {
        return isWild;
    }

    public int getGameId()
    {
        return _i_gameId;
    }

    public int getFirstSecondCardPick() {
        return FirstSecondCardPick;
    }
}
