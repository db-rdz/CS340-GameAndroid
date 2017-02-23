package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyanBlaser on 2/12/17.
 */

public class Poller {
    private final Timer timer = new Timer();
    private TimerTask timerTask;

    public Poller()
    {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//					checkForCommands();
//				} catch (GameIsFullException e) {
//					e.printStackTrace();
//				}
//            }
//        };

        long delay = 10000; //10 seconds
//        long delay = 60000; //counts in milisecs so 1min.

//        timer.schedule(timerTask, delay);
    }

    public CommandContainer checkForCommands() throws GameIsFullException
    {
        CommandContainer result = ServerProxy.SINGLETON.checkForCommands();
        ICommand command;
        for (int i = 0; i < result.str_type.size(); i++)
        {
            switch (result.str_type.get(i)) {
                case "AddJoinableCommand" :
                    Number joinableSize = (Number) result.icommand.get(i);
                    command = new AddJoinableToClientCommand(joinableSize.intValue());
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
}