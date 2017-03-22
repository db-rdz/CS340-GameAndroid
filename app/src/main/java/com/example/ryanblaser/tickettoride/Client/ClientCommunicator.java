package com.example.ryanblaser.tickettoride.Client;

import android.os.AsyncTask;
import android.util.Log;


import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.Phase1.*;
import com.example.ryanblaser.tickettoride.Deserializers.PairDeserializer;
import com.example.ryanblaser.tickettoride.Serializers.PairSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by natha on 2/21/2017.
 */

public class ClientCommunicator extends AsyncTask<URL, Void, Integer> {

    /**
     * This is what will be used in the ServerProy class to set up ClientCommunicator objects.
     */
    public interface TaskListener
    {
        boolean onFinished(ICommand result);
    }

    private ObjectMapper objectMapper;
    private ICommand command;
    private String string_urlSuffix;
    private ICommand cmd; //Will return this at the end
    private List<ICommand> respondData;

    public ClientCommunicator(String urlSuffix, ICommand command) {
        this.command = command;
        string_urlSuffix = urlSuffix;
        objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        cmd = null;
        respondData = new ArrayList<>();
    }

    /**
     * Nathan
     * This allows Jackson to deserialize the apache.lang3.tuple.Pair class properly.
     */
    public void addPairModule() {
        final SimpleModule module = new SimpleModule();
        module.addSerializer(Pair.class, new PairSerializer());
        module.addDeserializer(Pair.class, new PairDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    protected Integer doInBackground(URL... urls) {
        addPairModule();

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
                String requestString = objectMapper.writeValueAsString(command);
                requestBody.write((requestString));
                requestBody.close();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    //DECODES FROM JSON
                    try {
                        InputStreamReader isr = new InputStreamReader(http.getInputStream());

                        List<ICommand> respondData = null;
                        TypeReference ref = new TypeReference<List<ICommand>>() { };
                        try {
                             respondData = objectMapper.readValue(http.getInputStream(), ref);
//                             respondData = objectMapper.readValue(http.getInputStream(), objectMapper.getTypeFactory().constructCollectionType(List.class, ICommand.class));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }


                        if (respondData != null) {
                            for (int i = 0; i < respondData.size(); i++) {
                                cmd = respondData.get(i);
                                cmd.execute();
                            }
                        }

                        return respondData.size();

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



        return respondData.size();
    }

    public ICommand getICommand() { return cmd; }
}
