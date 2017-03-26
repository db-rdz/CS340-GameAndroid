package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * Created by natha on 3/25/2017.
 */

public class RejectDestinationCardCommand implements ICommand {
    private int _i_gameId;
    private String _s_authenticationCode;
    private DestCard rejectedCard;

    public RejectDestinationCardCommand(int id, String code, DestCard card)
    {
        _i_gameId = id;
        _s_authenticationCode = code;
        rejectedCard = card;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        return null;
    }
    @Override
    public String getAuthenticationCode() {
        return _s_authenticationCode;
    }
    @Override
    public User getUser() {
        return null;
    }

    public int get_i_gameId()
    {
        return _i_gameId;
    }

    public String get_s_AuthenticationCode()
    {
        return _s_authenticationCode;
    }

    public DestCard getRejectedCard()
    {
        return rejectedCard;
    }

}