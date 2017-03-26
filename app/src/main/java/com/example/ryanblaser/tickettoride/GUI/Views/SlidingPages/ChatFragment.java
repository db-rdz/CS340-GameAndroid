package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ryanblaser.tickettoride.GUI.Presenters.ChatPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    EditText textMessageView;
    private ListView listView_chat;
    private ArrayAdapter<String> list_of_messages;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard(getContext());
    }

    /**
     * Nathan: Closes keyboard on fragment start up.
     * Keyboard pops up instantly because of the EditText for the chat
     * @param ctx Context of the fragment
     */
    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Nathan: Simply closes the keyboard once a message is sent.
     * @param c Context of activity
     * @param windowToken The view's token we're using
     */
    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        listView_chat = (ListView) v.findViewById(R.id.messagesList);

        textMessageView = (EditText) v.findViewById(R.id.text_message);

        Button sendMessage = (Button)v.findViewById(R.id.send);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMessage = textMessageView.getText().toString();

//                ChatPresenter._SINGLETON.getChat().add(textMessage); //Comment out when sending to server
                ChatPresenter._SINGLETON.sendMessage(textMessage); //uncomment when sending to server
                textMessageView.getText().clear(); //Clears the text after you send a message
                closeKeyboard(getActivity(), textMessageView.getWindowToken());
            }
        });

        return v;
    }



    @Override
    public void onResume() {
        super.onResume();

        List<String> chatroom = ChatPresenter._SINGLETON.getChat();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < chatroom.size(); i++) {
            messages.add(chatroom.get(i));
        }

        list_of_messages = new ArrayAdapter<String>(getContext(), R.layout.row_info, messages);
        listView_chat.setAdapter(list_of_messages);
        list_of_messages.notifyDataSetChanged();
    }

    public void refreshChat() {
        List<String> chatroom = ChatPresenter._SINGLETON.getChat();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < chatroom.size(); i++) {
            messages.add(chatroom.get(i));
        }

        list_of_messages = new ArrayAdapter<String>(getContext(), R.layout.row_info, messages);
        listView_chat.setAdapter(list_of_messages);
        list_of_messages.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
