package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.Phase1.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyanBlaser on 2/12/17.
 */

public class Poller implements Runnable {
    private final Timer timer = new Timer();
    private TimerTask timerTask;

    public Poller()
    {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
					checkForCommands();
				} catch (GameIsFullException e) {
					e.printStackTrace();
				}
            }
        };

        long delay = 10000; //10 seconds

        timer.schedule(timerTask, delay);
    }

    public CommandContainer checkForCommands() throws GameIsFullException
    {
        CommandContainer result = ServerProxy.SINGLETON.checkForCommands();
        ICommand command;
        for (int i = 0; i < result.str_type.size(); i++)
        {
            switch (result.str_type.get(i)) {
                case "AddJoinableCommand" :
                    command = new AddJoinableToClientCommand(result.icommand.get(i).getGame());
                    break;
                case "DeleteGameCommand" :
                    command = (DeleteGameCommand) result.icommand.get(i);
                    break;
                case "AddResumableCommand" :
                    command = (AddResumableToClientCommand) result.icommand.get(i);
                    break;
                case "AddPlayerCommand" :
                    command = (AddPlayerToClientCommand) result.icommand.get(i);
                    break;
                default:
                    command = null;
                    break;
            }
            command.execute();
        }
        return result;
    }

    public TimerTask getTimerTask() { return timerTask; }
    public void setTimerTask(TimerTask timerTask) { this.timerTask = timerTask; }

    @Override
    public void run() {
    }
}