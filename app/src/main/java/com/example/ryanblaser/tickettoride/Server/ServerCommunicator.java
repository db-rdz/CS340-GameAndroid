package com.example.ryanblaser.tickettoride.Server;

import com.example.ryanblaser.tickettoride.Client.ClientFacade;
import com.example.ryanblaser.tickettoride.Command.AddGameToServerCommand;
import com.example.ryanblaser.tickettoride.Command.AddPlayerToServerCommand;
import com.example.ryanblaser.tickettoride.Command.CommandContainer;
import com.example.ryanblaser.tickettoride.Command.DeleteGameCommand;
import com.example.ryanblaser.tickettoride.Command.GetCommandsCommand;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Command.LoginCommand;
import com.example.ryanblaser.tickettoride.Command.LogoutCommand;
import com.example.ryanblaser.tickettoride.Command.RegisterCommand;
import com.example.ryanblaser.tickettoride.Command.StartGameCommand;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by RyanBlaser on 2/6/17.
 */

public class ServerCommunicator {
    public static ServerCommunicator SINGLETON = new ServerCommunicator();

    private static final int MAX_WAITING_CONNECTION = 10;
    private HttpServer server;
    private static int SERVER_PORT_NUMBER;
    private Gson gson = new Gson();

    private static class InvalidAuthenticationCodeException extends Exception {

    }

    public static void main(String[] args)
    {
        SINGLETON.run();
    }

    private void run()
    {
        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER), MAX_WAITING_CONNECTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.setExecutor(null);
        server.createContext("/command", commandHandler);
        server.createContext("/", defaultHandler);
        server.start();
    }

    private HttpHandler commandHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            InputStreamReader isr = new InputStreamReader(is);
            CommandContainer input = gson.fromJson(isr, CommandContainer.class);
            ICommand command;
            // switch all command types
            switch (input.str_type.get(0)) {
                case "AddGameToServerCommand":
                    command = (AddGameToServerCommand) input.icommand.get(0);
                    break;
                case "AddPlayerToServerCommand":
                    command = (AddPlayerToServerCommand) input.icommand.get(0);
                    break;
                case "DeleteGameCommand":
                    command = (DeleteGameCommand) input.icommand.get(0);
                    break;
                case "GetCommandsCommand":
                    command = (GetCommandsCommand) input.icommand.get(0);
                    break;
                case "LoginCommand":
                    command = (LoginCommand) input.icommand.get(0);
                    break;
                case "LogoutCommand":
                    command = (LogoutCommand) input.icommand.get(0);
                    break;
                case "RegisterCommand":
                    command = (RegisterCommand) input.icommand.get(0);
                    break;
                case "StartGameCommand":
                    command = (StartGameCommand) input.icommand.get(0);
                    break;
                default:
                    throw new UnknownHostException();

            }
            // if the command has an authentication code, check it
            if (!(command instanceof RegisterCommand) && !(command instanceof LoginCommand))
            {
                if(command.getAuthenticationCode() != ClientFacade.SINGLETON.find(command.getUser()).getStr_authentication_code())
//                if(command.getAuthenticationCode() != ClientFacade.SINGLETON.getClientmodel().getUsers().find(command.getUser()).getAuthenticationCode())
                {
//                    throw new InvalidAuthenticationCodeException();
                    throw new IOException();
                }
            }
            command.execute();
            String response = "Did it!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    };

    private HttpHandler defaultHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

        }
    };
}
