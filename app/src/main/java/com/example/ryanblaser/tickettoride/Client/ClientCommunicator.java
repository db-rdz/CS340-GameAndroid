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
import com.example.ryanblaser.tickettoride.Command.LoginRegisterResponseCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutResponseCommand;
import com.example.ryanblaser.tickettoride.UserInfo.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;


/**
 * Created by natha on 2/6/2017.
 */

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private Gson gson;
//    private Gson other_gson;

    private ClientCommunicator() {
        gson = new Gson();
//    	gson = StreamIO.getCommandContainerRWer();
//    	other_gson = new Gson();
    }

    private static String port;
    private static String host;
    private static String other_host = "192.168.1.112";


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
    		
        	URL url = new URL("http://" + other_host + port + urlSuffix);
//			URL url = new URL("http://" + host + port + urlSuffix);
//            URL url = new URL("http://" + urlString + port + urlSuffix); //if only want localhost

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setConnectTimeout(5000);
            http.setDoOutput(true);

            http.connect();

            //ENCODES TO JSON
            try
            {
            	/** gson */
                OutputStreamWriter requestBody = new OutputStreamWriter(http.getOutputStream());
                
                //2-16-17 1:40am
                //IT WORKS NOW
        		String requestString = gson.toJson(commandContainer);
//        		System.out.println("commandcontainerJson: " + requestString);
        		
//        		CommandContainer responsebody = other_gson.fromJson(requestString, CommandContainer.class);
//        		System.out.println("responsebody: " + responsebody.str_type);
//        		System.out.println("responsebody: " + responsebody.icommand);
                
                
                requestBody.write((requestString));
                requestBody.close();
            	
            	/** Josh's code */
//                OutputStreamWriter requestBody = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
//                String requestString = gson.toJson(commandContainer); //Send a serialized CommandContainer
//                System.out.println("requestString to Json: " + requestString);
//                requestBody.write(requestString);
//                requestBody.flush();
//                requestBody.close();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //DECODES FROM JSON
                    try {
//                    	System.out.println("past the responseCode");
                        InputStreamReader isr = new InputStreamReader(http.getInputStream());
                        CommandContainer respondData = gson.fromJson(isr, CommandContainer.class); //Receive a serialized CommandContainer
//                        CommandContainer respondData = gson.fromJson(StreamIO.read(http.getInputStream()), CommandContainer.class); //Receive a serialized CommandContainer
//                        System.out.println("past the responseCode & fromJson");
//                    	System.out.println("input type: " + respondData.str_type);
//                    	System.out.println("input command: " + respondData.icommand);
//                    	for (int i = 0; i < respondData.icommand.size(); i++) {
//                    		System.out.println(respondData.icommand.get(i));
//                    	}

                    	ICommand cmd = null;

                        //TODO: CommandContainer will contain Lists now. So this switch will be in a loop now.
                        for (int i = 0; i < respondData.str_type.size(); i++) {
                            switch (respondData.str_type.get(i)) { //Make the corresponding command depending on the type of command.
//                                case "LoginCommand":
//                                	User user = new User();
//                            		user.setUsername((String) respondData.icommand.get(0));
//                            		user.setPassword((String) respondData.icommand.get(1));
//                            		user.setStr_authentication_code((String) respondData.icommand.get(2));
//                            		respondData.icommand.remove(2);
//                            		respondData.icommand.remove(1);
//                            		respondData.icommand.remove(0);
//                                    cmd = new LoginCommand(user.getUsername(), user.getPassword(), user.getStr_authentication_code());
//                                    cmd.execute();
//                                    break;

//                                case "RegisterCommand":
//                                    cmd = (RegisterCommand) respondData.icommand;
//                                    cmd.execute();
//                                    break;

                                case "AddJoinableCommand":
                                	cmd = new AddJoinableToClientCommand((int)respondData.icommand.get(0));
                                	respondData.icommand.remove(0); //Gets rid of the first object in the list to accomodate for the next command called
                                    cmd.execute();
                                    break;

                                case "AddResumableCommand":
                                	cmd = new AddResumableToClientCommand((int)respondData.icommand.get(0));
                                	respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "AddWaitingCommand":
                                	cmd = new AddWaitingToClientCommand((int)respondData.icommand.get(0));
                                	respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

//                                case "StartGame":
//                                    cmd = (StartGameCommand) respondData.icommand;
//                                    cmd.execute();

                                case "AddPlayerCommand":
                                    cmd = (AddPlayerToClientCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

//                                case "Logout":
//                                    cmd = (LogoutCommand) respondData.icommand;
//                                    cmd.execute();
//                                    break;

                                case "ListJoinableCommand":
                                	cmd = new ListJoinableCommand((List<Integer>) respondData.icommand.get(0));
                                	respondData.icommand.remove(0);
//                                    cmd = (ListJoinableCommand) respondData.icommand;
                                    cmd.execute();
                                    break;

                                case "ListResumableCommand":
//                                    cmd = (ListResumableCommand) respondData.icommand;
                                	cmd = new ListResumableCommand((List<Integer>) respondData.icommand.get(0));
                                	respondData.icommand.remove(0);
                                    cmd.execute();
                                    break;

                                case "ListWaitingCommand":
//                                    cmd = (ListWaitingCommand) respondData.icommand;
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
