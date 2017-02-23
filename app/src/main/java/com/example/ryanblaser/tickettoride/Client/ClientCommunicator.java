package com.example.ryanblaser.tickettoride.Client;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.ListResumableCommand;
import com.example.ryanblaser.tickettoride.Command.ListWaitingCommand;
import com.example.ryanblaser.tickettoride.Command.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

/**
 * Created by natha on 2/21/2017.
 */

public class ClientCommunicator extends AsyncTask<URL, Void, ICommand> {

    /**
     * This is what will be used in the ServerProy class to set up ClientCommunicator objects.
     */
    public interface TaskListener
    {
        boolean onFinished(ICommand result);
    }

    private Gson gson;
//    private final TaskListener taskListener;
    private CommandContainer commandContainer;
    private String string_urlSuffix;
    private ICommand cmd; //Will return this at the end

    public ClientCommunicator(String urlSuffix, CommandContainer commandContainer) {
//        this.taskListener = taskListener;
        this.commandContainer = commandContainer;
        string_urlSuffix = urlSuffix;
        gson = new Gson();
        cmd = null;
    }


    @Override
    protected ICommand doInBackground(URL... urls) {


        for (URL url : urls) {
            try {
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setConnectTimeout(5000);
                http.setDoOutput(true);

                http.connect();

                OutputStreamWriter requestBody = new OutputStreamWriter(http.getOutputStream());

                //2-16-17 1:40am
                //IT WORKS NOW
                String requestString = gson.toJson(commandContainer);

                requestBody.write((requestString));
                requestBody.close();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //DECODES FROM JSON
                    try {
                        InputStreamReader isr = new InputStreamReader(http.getInputStream());
                        CommandContainer respondData = gson.fromJson(isr, CommandContainer.class); //Receive a serialized CommandContainer

                        //TODO: CommandContainer will contain Lists now. So this switch will be in a loop now.
                        for (int i = 0; i < respondData.str_type.size(); i++) {
                            switch (respondData.str_type.get(i)) { //Make the corresponding command depending on the type of command.

                                case "GetCommandsCommand":
                                    cmd = new GetCommandsCommand(respondData.str_type);
                                    cmd.execute();
                                    break;

                                case "AddJoinableCommand":
                                    Number joinableSize = (Number) respondData.icommand.get(0);
                                    cmd = new AddJoinableToClientCommand(joinableSize.intValue());
                                    respondData.icommand.remove(0); //Gets rid of the first object in the list to accomodate for the next command called
                                    cmd.execute();
                                    break;

                                case "AddResumableCommand":
                                    cmd = new AddResumableToClientCommand((int)respondData.icommand.get(0));
                                    respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "AddWaitingCommand":
                                    Number waitingSize = (Number) respondData.icommand.get(0);
                                    cmd = new AddWaitingToClientCommand(waitingSize.intValue());
                                    respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "AddPlayerCommand":
                                    cmd = (AddPlayerToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "ListJoinableCommand":
                                    cmd = new ListJoinableCommand((List<Integer>) respondData.icommand.get(0));
                                    respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "ListResumableCommand":
                                    cmd = new ListResumableCommand((List<Integer>) respondData.icommand.get(0));
                                    respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "ListWaitingCommand":
                                    cmd = new ListWaitingCommand((List<Integer>) respondData.icommand.get(0));
                                    respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "LoginRegisterResponseCommand":
                                    User user2 = new User();
                                    user2.setUsername((String) respondData.icommand.get(0));
                                    user2.setPassword((String) respondData.icommand.get(1));
                                    user2.setStr_authentication_code((String) respondData.icommand.get(2));
                                    respondData.icommand.remove(2);
                                    respondData.icommand.remove(1);
                                    respondData.icommand.remove(0);
                                    cmd = new LoginRegisterResponseCommand(user2.getUsername(), user2.getPassword(), user2.getStr_authentication_code());
                                    cmd.execute();
                                    break;

                                case "LogoutResponseCommand":
                                    cmd = new LogoutResponseCommand();
                                    cmd.execute();
                                    break;

                                default:
                                    cmd = null; //If nothing was received
                                    break;
                            }
                        }
                        return cmd;

                    }
                    catch (Exception e) //InputStreamReader
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("ERROR: " + http.getResponseMessage());
//                    return "ERROR @ ClientCommunicator_old send()";
                }

//                ClientCommunicator_old.SINGLETON.send(string_urlSuffix, commandContainer);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return null;
    }

    public ICommand getICommand() { return cmd; }
}
