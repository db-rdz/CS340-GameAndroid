package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.GetCommandsCommand;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyanBlaser on 2/12/17.
 */

public class Poller {
    private final Timer timer = new Timer();
    public Poller()
    {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                checkForCommands();
            }
        };

        long delay = 60000; //counts in milisecs so 1min.

        timer.schedule(timerTask, delay);
    }

    public void checkForCommands()
    {
        GetCommandsCommand getCommandsCommand = new GetCommandsCommand();
    }
}
