package com.example.ryanblaser.tickettoride.Server;

import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.UserInfo.Username;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by RyanBlaser on 2/13/17.
 */

public class ClientProxy
{
    public static ClientProxy SINGLETON = new ClientProxy();

    public ClientProxy()
    {
        _m_usersCommands = new HashMap<>();
        commandTypes = new ArrayList<>();
    }

    private Map<Username, CommandContainer> _m_usersCommands;

    private List<String> commandTypes;

    public Map<Username, CommandContainer> get_m_usersCommands() {
        return _m_usersCommands;
    }

    public void set_m_usersCommands(Map<Username, CommandContainer> _m_usersCommands) {
        this._m_usersCommands = _m_usersCommands;
    }

    public CommandContainer getUserCommands(Username username)
    {
        return _m_usersCommands.get(username);
    }
}