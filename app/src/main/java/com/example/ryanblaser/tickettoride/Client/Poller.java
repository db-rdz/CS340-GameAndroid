package com.example.ryanblaser.tickettoride.Client;

<<<<<<< HEAD
import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
=======
import com.example.ryanblaser.tickettoride.Command.Phase1.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyanBlaser on 2/12/17.
 */

<<<<<<< HEAD
public class Poller {
=======
public class Poller implements Runnable {
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
    private final Timer timer = new Timer();
    private TimerTask timerTask;

    public Poller()
    {
<<<<<<< HEAD
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
=======
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
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
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
<<<<<<< HEAD
=======

    @Override
    public void run() {
    }
>>>>>>> 960a86b1539ed8a6872c5df4b399c4b605bfe5a8
}