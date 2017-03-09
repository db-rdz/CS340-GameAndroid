package com.example.ryanblaser.tickettoride.Database;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by benjamin on 11/02/17.
 */
public class DAO implements iDAO {
    public static iDAO _SINGLETON = new DAO();
    private static DataBase _db;
    private int AUTH_TOKEN_LENGTH = 15;
    private static int int_gameId = 1;

    public DAO(){
        initializeDB();
    }

    @Override
    public User getUserFromId(int userId) {
        return null;
    }

//    @Override
//    public int getUserId(String userName) {
//        return 0;
//    }

    @Override
    public Game getGameFromId(int gameId){
        return null;
    }

    @Override
    public Boolean login(String userName, String password) throws SQLException, IClient.InvalidUsername, IClient.InvalidPassword, IClient.UserAlreadyLoggedIn {
        User dbUser = getUserByUserName(userName);
        if(dbUser == null) {
            return false;
        }
        if (!dbUser.get_S_username().equals(userName)) {
            throw new IClient.InvalidUsername();
        }
        if(password.equals(dbUser.get_S_password())) {
            updateUserToken(userName, makeToken());
            return true;
        }
        else { //if the password doesn't match
            throw new IClient.InvalidPassword();
        }
//        return false;
    }

    @Override
    public Boolean authenticateUserWithToken(String token) throws SQLException {
        try {
            return getUserByAccessToken(token) != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUserName(String username) throws SQLException, IClient.UserAlreadyLoggedIn
    {
        _db.openConnection(); //This allows the database to be accessed everytime this method is called.
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User readUser = new User();
        try
        {

            String sql = "select * from users where users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            readUser.set_S_username(resultSet.getString(1));
            readUser.set_S_password(resultSet.getString(2));
            readUser.set_S_token(resultSet.getString(3));

            if (readUser.get_S_username().equals(null)) { throw new IClient.InvalidUsername(); }
            if (readUser.get_S_password().equals(null)) { throw new IClient.InvalidPassword(); }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());

        }
        catch (Exception e) {
//        	e.printStackTrace();
        }

        finally
        {
            if(statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return readUser;
    }

    public User getUserByAccessToken(String token) throws SQLException
    {
        _db.openConnection();

        PreparedStatement statement = null;
        ResultSet rs = null;
        User readUser = null;
        try
        {
            String sql = "select * from Users where Users.token = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, token);
            rs = statement.executeQuery();
            while(rs.next())
            {
                readUser = new User();
                readUser.set_S_username(rs.getString(1));
                readUser.set_S_password(rs.getString(2));
                readUser.set_S_token(rs.getString(3));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if(statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }
        return readUser;
    }

    @Override
    public Boolean registerUser(String userName, String password) throws SQLException {
        if(userName == null || password == null){
            return false;
        }
        _db.openConnection();

        PreparedStatement registerUserStatement = null;
        ResultSet keyRS = null;
        boolean success = false;
        String registerUserSQL = "insert into Users (userName, password, token)"
                + "values (?, ?, ?)";
        try {
            registerUserStatement = _db.connection.prepareStatement(registerUserSQL);
            registerUserStatement.setString(1, userName);
            registerUserStatement.setString(2, password);
            String token = makeToken();
            registerUserStatement.setString(3, token);

            if(registerUserStatement.executeUpdate() == 1){
                success = true;
                updateUserToken(userName, token);
            }
            else {
                throw new SQLException();
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(registerUserStatement != null) {
                registerUserStatement.close();
            }
            if (keyRS != null)
                keyRS.close();
        }


        return success;
    }

    @Override
    public Boolean addGame(Game game) throws SQLException {
        if (game == null) { return false; }
        _db.openConnection();

        PreparedStatement gameStatement = null;
        ResultSet keyRS = null;
        boolean success = false;
        String registerUserSQL = "insert into Games (gameId, numberOfPlayers)"
                + "values (?, ?)";

        try {
            gameStatement = _db.connection.prepareStatement(registerUserSQL);
            gameStatement.setInt(1, int_gameId++); //increments the int by 1 after it's added to the table
            gameStatement.setInt(2, game.get_numberOfPlayers());
//            gameStatement.setBoolean(2, game.get_S_active());

            if(gameStatement.executeUpdate() == 1){
                success = true;
            }
            else {
                throw new SQLException();
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(gameStatement != null) {
                gameStatement.close();
            }
            if (keyRS != null)
                keyRS.close();
        }

        return success;
    }

    @Override
    public Game getGamebyGameId(int gameId) throws SQLException {
        _db.openConnection(); //This allows the database to be accessed everytime this method is called.

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Game readGame = new Game();
        try
        {

            String sql = "select * from games where Games.gameId = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            resultSet = statement.executeQuery();

            readGame.set_i_gameId(resultSet.getInt(1));
            readGame.set_numberOfPlayers(resultSet.getInt(2));

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());

        }
        catch (Exception e) {
//        	e.printStackTrace();
        }

        finally
        {
            if(statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return readGame;

    }

    @Override
    public List<Game> getGamesByUserName(String userName) throws SQLException {
        _db.openConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<Game>();
        try {
            String getGamesSQL = "select * from Games where Games.id in " +
                    "(select gameId from UserGames where UserGames.userName = ?)";
            statement = _db.connection.prepareStatement(getGamesSQL);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Game game = new Game();
                game.set_S_gameName(resultSet.getString(1));
                game.set_numberOfPlayers(resultSet.getInt(2));
                game.set_S_active(resultSet.getBoolean(3));
                gamesList.add(game);
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return gamesList;
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
        _db.openConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<Game>();

        try {
            String getGamesSQL = "select * from Games";
            statement = _db.connection.prepareStatement(getGamesSQL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Game game = new Game();
                game.set_i_gameId(resultSet.getInt(1)); //Grabs the gameId
//                game.set_S_gameName(resultSet.getString(2)); //Grabs the game name
                game.set_numberOfPlayers(resultSet.getInt(2)); //Grabs the current number of players in the game
//                game.set_S_active(resultSet.getBoolean(4)); //Grabs the boolean value if it's active or not.
                gamesList.add(game);
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return gamesList;
    }

    public void deleteAllGames() throws SQLException {
        _db.openConnection();

        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        List<Game> gamesList = new ArrayList<Game>();

        try {
            String getGamesSQL = "DROP TABLE IF EXISTS Games;";
            statement = _db.connection.prepareStatement(getGamesSQL);
            statement.execute();

            String createGamesTableSQL =
                    "CREATE TABLE IF NOT EXISTS Games" +
                            "(" +
                            "id integer not null primary key autoincrement," +
                            "gameId integer not null," +
                            "numberOfPlayers integer" +
                            "active bit" +
                            ");";

            statement2 = _db.connection.prepareStatement(createGamesTableSQL);
            statement2.execute();

//            while (resultSet.next()) {
//                Game game = new Game();
//                game.set_i_gameId(resultSet.getInt(1)); //Grabs the gameId
//                game.set_S_gameName(resultSet.getString(2)); //Grabs the game name
//                game.set_numberOfPlayers(resultSet.getInt(2)); //Grabs the current number of players in the game
//                game.set_S_active(resultSet.getBoolean(4)); //Grabs the boolean value if it's active or not.
//                gamesList.add(game);
//            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        finally {
            if (statement != null || statement2 != null) {
                statement.close();
                statement2.close();
            }


            if (resultSet != null || resultSet2 != null) {
                statement.close();
                statement2.close();
            }

        }
//        return gamesList;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        _db.openConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        try {
            String getUsersSQL = "select * from Users";
            statement = _db.connection.prepareStatement(getUsersSQL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.set_S_username(resultSet.getString(1));
                user.set_S_password(resultSet.getString(2));
                user.set_S_token(resultSet.getString(3));
                userList.add(user);
            }

        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return userList;
    }

    @Override
    public void initializeDB() {
        _db = new DataBase();
    }

    private void updateUserToken(String userName, String token)
    {
        _db.openConnection();

        PreparedStatement statement = null;
        try
        {
            String sql = "Update Users set token = ? where Users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, userName);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
                statement = null;
        }
    }

    private String makeToken()
    {
        return RandomGenerator.getInstance().randomUUID().substring(0, AUTH_TOKEN_LENGTH);
    }

    @Override
    public DataBase getDb() { return _db; }
}