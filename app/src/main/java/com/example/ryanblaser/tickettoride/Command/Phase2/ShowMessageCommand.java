package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * This command occurs when a player taps "Messages" on the screen.
 * This will cause a display to occur and show what has been broadcasted in the game chat.
 *
 * Created by natha on 2/28/2017.
 */

public class ShowMessageCommand implements ICommand {

    //Data members
    //Ryan: updated entire list instead of just adding one message
    private List<String> chatRoom;

    //Constructor
    public ShowMessageCommand(List<String> chat) {
        chatRoom = chat;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        ClientFacade.SINGLETON.showMessage(chatRoom);
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public List<String> getChatRoom() {
        return chatRoom;
    }
}
