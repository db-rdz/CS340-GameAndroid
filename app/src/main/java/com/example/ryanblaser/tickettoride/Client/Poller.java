package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.Phase1.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.GUI.Activities.MainActivity;
import com.example.ryanblaser.tickettoride.Server.IServer.GameIsFullException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyanBlaser on 2/12/17.
 */


public class Poller implements Runnable {
    
    private final Timer timer = new Timer();
    private TimerTask timerTask;
    private User user;
    
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
        
        timer.schedule(timerTask, delay, delay);
        
        user = null;
    }
    
    public CommandContainer checkForCommands() throws GameIsFullException
    {
        if (user != null) {
            CommandContainer result = ServerProxy.SINGLETON.checkForCommands(user.getUsername());
            return result;
        }
        return null;
    }
    
    public TimerTask getTimerTask() { return timerTask; }
    public void setTimerTask(TimerTask timerTask) { this.timerTask = timerTask; }
    
    public Timer getTimer() {
        return timer;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public void run() {
    }
}
