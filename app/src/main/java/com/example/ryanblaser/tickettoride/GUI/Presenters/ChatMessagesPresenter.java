package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 13/03/17.
 */

public class ChatMessagesPresenter {
    public static ChatMessagesPresenter _SINGLETON = new ChatMessagesPresenter();

    List<String> chat;

    public ChatMessagesPresenter() {
        chat = ClientFacade.SINGLETON.getClientModel().getChat();
    }
    public void sendMessage(String message){
        ClientFacade.SINGLETON.broadcastToChat(message);
    }


    public List<String> getChat() {
        return chat;
    }

    public void setChat(List<String> chat) {
        this.chat = chat;
    }
}
