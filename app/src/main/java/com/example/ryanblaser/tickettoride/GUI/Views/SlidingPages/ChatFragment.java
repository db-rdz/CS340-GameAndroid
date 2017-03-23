package com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;
import com.example.ryanblaser.tickettoride.R;

import java.util.List;

public class ChatFragment extends Fragment {

    EditText textMessageView;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        List<String> messages = ClientFacade.SINGLETON.getClientModel().getChat();
        ListView list = (ListView) v.findViewById(R.id.messagesList);

        textMessageView = (EditText) v.findViewById(R.id.text_message);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textMessageView.getWindowToken(), 0);

        Button sendMessage = (Button)v.findViewById(R.id.send);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMessage = textMessageView.getText().toString();

                ClientFacade.SINGLETON.getClientModel().getChat().add(textMessage);
//                GameBoardPresenter._SINGLETON.sendMessage(textMessage);
                textMessageView.getText().clear(); //Clears the text after you send a message
                closeKeyboard(getActivity(), textMessageView.getWindowToken());
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.message_view, messages);
        list.setAdapter(adapter);

        return v;
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
