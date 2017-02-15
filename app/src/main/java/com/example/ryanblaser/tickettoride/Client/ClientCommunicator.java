package com.example.ryanblaser.tickettoride.Client;

import com.example.ryanblaser.tickettoride.Command.AddJoinableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddResumableToClientCommand;
import com.example.ryanblaser.tickettoride.Command.AddWaitingToClientCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.ListJoinableCommand;
import com.example.ryanblaser.tickettoride.Command.ListResumableCommand;
import com.example.ryanblaser.tickettoride.Command.ListWaitingCommand;
import com.example.ryanblaser.tickettoride.Command.LoginCommand;
import com.example.ryanblaser.tickettoride.Command.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.Command.RegisterCommand;
import com.example.ryanblaser.tickettoride.Command.StartGameCommand;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by natha on 2/6/2017.
 */

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private Gson gson;

    private ClientCommunicator() {
        gson = new Gson();
    }

    private static String port;
    private static String host;


    public ICommand send(String urlSuffix , CommandContainer commandContainer) throws IOException
    {
        String urlString = "localhost";
        port = ":8080"; //default port I'm using

        //Grabs my computer ip address and allows other computers to access my server.
        try {
            host = InetAddress.getLocalHost().getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
			URL url = new URL("http://" + host + port + urlSuffix);
//            URL url = new URL("http://" + urlString + port + urlSuffix); //if only want localhost

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setConnectTimeout(5000);
            http.setDoOutput(true);

            http.connect();

            //ENCODES TO JSON
            try
            {
                OutputStreamWriter requestBody = new OutputStreamWriter(http.getOutputStream());
                String requestString = gson.toJson(commandContainer); //Send a serialized CommandContainer
                requestBody.write(gson.toJson(requestString));

                requestBody.close();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //DECODES FROM JSON
                    try {
                        InputStreamReader isr = new InputStreamReader(http.getInputStream());
                        CommandContainer respondData = gson.fromJson(isr, CommandContainer.class); //Receive a serialized CommandContainer
                        ICommand cmd;

                        //TODO: CommandContainer will contain Lists now. So this switch will be in a loop now.
                        for (int i = 0; i < respondData.str_type.size(); i++) {
                            switch (respondData.str_type.get(i)) { //Make the corresponding command depending on the type of command.
                                case "LoginCommand":
                                    cmd = (LoginCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "RegisterCommand":
                                    cmd = (RegisterCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "AddJoinable":
                                    cmd = (AddJoinableToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "AddResumable":
                                    cmd = (AddResumableToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "AddWaiting":
                                    cmd = (AddWaitingToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "StartGame":
                                    cmd = (StartGameCommand) respondData.icommand;
                                    cmd.execute();

                                case "AddPlayer":
                                    cmd = (AddPlayerToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "Logout":
                                    cmd = (LogoutCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "ListJoinable":
                                    cmd = (ListJoinableCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "ListResumable":
                                    cmd = (ListResumableCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "ListWaiting":
                                    cmd = (ListWaitingCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "LoginRegisterResponse":
                                    cmd = (LoginRegisterResponseCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "LogoutResponse":
                                    cmd = (LogoutResponseCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                default:
                                    cmd = null; //If nothing was received
                                    break;
                            }
                        }
                        return null;

                    }
                    catch (Exception e) //InputStreamReader
                    {
                        e.printStackTrace();

                    }
                }
                else
                {
                    System.out.println("ERROR: " + http.getResponseMessage());
//                    return "ERROR @ ClientCommunicator send()";
                }
            }
            catch (Exception e) //OutputStreamWriter
            {
                e.printStackTrace();
            }
        }
        catch (Exception e) //url
        {
            e.printStackTrace();
        }

        return null;

    }

}
