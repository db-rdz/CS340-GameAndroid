package com.example.ryanblaser.tickettoride.Client;


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
    
    public void checkForCommands() throws GameIsFullException
    {
        if (user != null) {
            ServerProxy.SINGLETON.checkForCommands(user.getUsername());
        }
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
