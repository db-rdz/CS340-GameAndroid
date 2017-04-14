package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.ChatFragment;

import java.util.List;

/**
 * Created by benjamin on 13/03/17.
 */

public class ChatPresenter {
    public static ChatPresenter _SINGLETON = new ChatPresenter();

    private  ChatFragment chatFragment = new ChatFragment();

    public ChatPresenter() {

    }
    public void sendMessage(String message){
        ClientFacade.SINGLETON.broadcastToChat(message);
    }


    public List<String> getChat() {
        return ClientFacade.SINGLETON.getClientModel().getChat();
    }

    public void setChat(List<String> chat) {
        ClientFacade.SINGLETON.getClientModel().setChat(chat);
    }

    public void refreshChat() {
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().setChatFragment(chatFragment);
        ClientFacade.SINGLETON.getClientModel().getBoardActivity().refreshChat();
    }

    public void setChatFragment(ChatFragment fragment)
    {
        chatFragment = fragment;
    }
}
