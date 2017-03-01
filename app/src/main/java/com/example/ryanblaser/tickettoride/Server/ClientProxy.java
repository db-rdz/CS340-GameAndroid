package com.example.ryanblaser.tickettoride.Server;

<<<<<<< HEAD
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
=======
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RyanBlaser on 2/13/17.
 */

public class ClientProxy
{
    public static com.example.ryanblaser.tickettoride.Server.ClientProxy SINGLETON = new com.example.ryanblaser.tickettoride.Server.ClientProxy();

    public ClientProxy()
    {
        _m_usersCommands = new HashMap<>();
        commandTypes = new ArrayList<>();
    }

    private Map<String, CommandContainer> _m_usersCommands;

    private List<String> commandTypes;

    public Map<String, CommandContainer> get_m_usersCommands() {
        return _m_usersCommands;
    }

    public void set_m_usersCommands(Map<String, CommandContainer> _m_usersCommands) {
        this._m_usersCommands = _m_usersCommands;
    }

    public CommandContainer getUserCommands(String username)
    {
        return _m_usersCommands.get(username);
    }
}