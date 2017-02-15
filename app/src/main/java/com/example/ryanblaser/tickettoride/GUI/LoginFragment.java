package com.example.ryanblaser.tickettoride.GUI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.R;
import com.example.ryanblaser.tickettoride.UserInfo.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener; //Probably don't need this

    //EditText data members are the parts where the user types in their login info.
    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_server; //TODO: How do we pass in the server address and port to connect to the server??
    private EditText editText_port;

    //Button data members will contact the server through the client side
    private Button button_login;
    private Button button_register;

    //User data member will contain all the info of the user trying to log in.
    private User user;

    /**
     * Will hold the typed in username for login
     */
    private static String string_username;

    /**
     * Will hold the typed in password for login
     */
    private static String string_password;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user The user currently using the app.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance(User user) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(string_username, user.getUsername());
        args.putString(string_password, user.getPassword());
        //TODO: What about the server and port?

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is how Nathan did his OnCreate in Family Map.
        if (getArguments() != null) {

            //This basically instantiates the user object
            user = new User();
            user.setUsername(getArguments().getString(string_username));
            user.setPassword(getArguments().getString(string_password));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false); //Sets the view to grab from the login fragment

        //This allows the app to update what we type in the blanks.
        editText_username = (EditText) view.findViewById(R.id.editText_username);
        editText_password = (EditText) view.findViewById(R.id.editText_password);
        editText_server = (EditText) view.findViewById(R.id.editText_server);
        editText_port = (EditText) view.findViewById(R.id.editText_port);

        //This part links the buttons to the code.
        button_login = (Button) view.findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onLoginButtonPressed();
                } catch (IClient.InvalidPassword invalidPassword) {
                    invalidPassword.printStackTrace();
                } catch (IClient.InvalidUsername invalidUsername) {
                    invalidUsername.printStackTrace();
                }
            }
        });

        button_register = (Button) view.findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onRegisterButtonPressed();
                } catch (IClient.UsernameAlreadyExists usernameAlreadyExists) {
                    usernameAlreadyExists.printStackTrace();
                }
            }
        });

        return view;
    }



    /*
     * This grabs the info the user typed into the app, and puts it into the actual user object of this fragment.
     * After this function is called, then the server will attempt to login/register with the user info.
     */
    public User getUserInfo() {
        user.setUsername(editText_username.getText().toString());
        user.setUsername(editText_password.getText().toString());

        return user;
    }

    /*
     * This method will contact the server, send the login info for the user to the server to check it,
     * the server will send back an authorization code if the login info is valid, and the user logs in.
     */
    public void onLoginButtonPressed() throws IClient.InvalidPassword, IClient.InvalidUsername {
        Toast.makeText(getContext(), "Logging in...", Toast.LENGTH_SHORT).show();

        //Gets the user login info from ClientModel and tries logging into the Server.
        ClientFacade.SINGLETON.setCurrentUser(user); //Should set the fragment user object to the ClientModel user object.
        ClientFacade.SINGLETON.login(user); //Guessing how it'll work right now
    }

    /*
     * This method will contact the server, send the register info for the user to the server
     * the server will check if the username already exists in the database and if the password is valid, then it
     * sends back an authorization code if the login info is valid, and the user logs in.
     *
     * If the username is already in the server database, then the server throws a UsernameAlreadyExists exception.
     */
    public void onRegisterButtonPressed() throws IClient.UsernameAlreadyExists {
        Toast.makeText(getContext(), "Registering new user...", Toast.LENGTH_SHORT).show();

        //Checks the server database if the username has been taken.
        ClientFacade.SINGLETON.setCurrentUser(user);
        ClientFacade.SINGLETON.register(user); //Guessing how it'll work right now
    }

//    TODO: check if we need callback functionality
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * Do we need this?
     *
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
