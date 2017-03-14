package com.example.ryanblaser.tickettoride.GUI.Presenters;

import com.example.ryanblaser.tickettoride.Client.ClientModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 13/03/17.
 */

public class ChatMessagesPresenter {
    public static ChatMessagesPresenter _SINGLETON = new ChatMessagesPresenter();
    private ClientModel _clientModel = new ClientModel();
    public List<String> getMessages(){
        List<String> dummyData = new ArrayList<String>();
//      List<String> messagesList = _clientModel.getChat();
        dummyData.add("This is the first message");
        dummyData.add("Ohhh thank you ur awesome!");
        dummyData.add("yes sirrrrr");
        dummyData.add("workkkkkk asdasdasd");
        return dummyData;
    }
}
