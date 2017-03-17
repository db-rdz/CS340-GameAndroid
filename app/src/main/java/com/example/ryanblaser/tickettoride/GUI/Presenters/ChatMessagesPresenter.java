package com.example.ryanblaser.tickettoride.GUI.Presenters;

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
        chat = new ArrayList<>();
    }

    public List<String> getChat() {
        return chat;
    }

    public void setChat(List<String> chat) {
        this.chat = chat;
    }
}
