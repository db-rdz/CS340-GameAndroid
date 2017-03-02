package com.example.ryanblaser.tickettoride.Client;

import android.os.AsyncTask;


import com.example.ryanblaser.tickettoride.Command.Phase1.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
    private ObjectMapper objectMapper;
    private SimpleModule module_login_register_response;
    private SimpleModule module_add_joinable;
    private CommandContainer commandContainer;
    private String string_urlSuffix;
    private ICommand cmd; //Will return this at the end

    public ClientCommunicator(String urlSuffix, CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
        string_urlSuffix = urlSuffix;
        gson = new Gson();
        objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
//                String requestString = gson.toJson(commandContainer);
                String requestString = objectMapper.writeValueAsString(commandContainer);
                requestBody.write((requestString));
                requestBody.close();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //DECODES FROM JSON
                    try {
                        InputStreamReader isr = new InputStreamReader(http.getInputStream());

                        CommandContainer respondData = null;
                        try {
                             respondData = objectMapper.readValue(http.getInputStream(), CommandContainer.class);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }


                        if (respondData != null) {
                            for (int i = 0; i < respondData.str_type.size(); i++) {
                                switch (respondData.str_type.get(i)) { //Make the corresponding command depending on the type of command.

                                    case "AddJoinableCommand":
                                        cmd = respondData.icommand.get(0);
                                        respondData.icommand.remove(0); //Gets rid of the first object in the list to accomodate for the next command called
                                        cmd.execute();
                                        break;

                                    case "AddResumableCommand":
//                                    cmd = new AddResumableToClientCommand((int)respondData.icommand.get(0));
                                        respondData.icommand.remove(0);
                                        cmd.execute();
                                        break;

                                    case "AddWaitingCommand":
                                        //cmd = new AddWaitingToClientCommand(respondData.icommand.get(0).getGame());
                                        cmd = respondData.icommand.get(0);
                                        respondData.icommand.remove(0);
                                        cmd.execute();
                                        break;

                                    case "AddPlayerCommand":
                                        cmd = respondData.icommand.get(0);
                                        cmd.execute();
                                        break;

                                    case "ListJoinableCommand":
                                        //cmd = new ListJoinableCommand((List<Integer>) respondData.icommand.get(0));
                                        cmd = respondData.icommand.get(0);
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
//                                    cmd = new LoginRegisterResponseCommand(respondData.icommand.get(0).getUser());
                                        cmd = respondData.icommand.get(0);
                                        respondData.icommand.remove(0);
                                        cmd.execute();
                                        break;

                                    case "LogoutResponseCommand":
                                        cmd = new LogoutResponseCommand();
                                        respondData.icommand.remove(0);
                                        cmd.execute();
                                        break;

                                    default:
                                        cmd = null; //If nothing was received
                                        break;
                                }
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
