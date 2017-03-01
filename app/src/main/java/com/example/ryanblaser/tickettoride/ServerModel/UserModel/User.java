package com.example.ryanblaser.tickettoride.ServerModel.UserModel;

import com.example.ryanblaser.tickettoride.Database.DAO;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class User implements iUser {

    //-----------------------------------------CLASS VARIABLES-------------------------------------------------//
    private String _S_username = null;
    private String _S_password = null;
    private String _S_token = null;

    private Boolean _B_isInGame = false;
    private List<Game> _L_joinedGames = new ArrayList<>();

    //_________________________________________________________________________________________________________//




    //-----------------------------------------STATIC VARIABLES------------------------------------------------//
    /**Maps a string () to a user*/
    /** Note: The function of finding a user with a determined id I think should be done here to keep us organized */
    private static Map<String, com.example.ryanblaser.tickettoride.ServerModel.UserModel.User> _M_idToUser = new HashMap();

    //_________________________________________________________________________________________________________//



    //-----------------------------------------STATIC FUNCTIONS------------------------------------------------//
    public static com.example.ryanblaser.tickettoride.ServerModel.UserModel.User getUserWithUsername(String username){ return _M_idToUser.get(username); }

    public static Boolean addLoggedInUser(String username){
        try {
            com.example.ryanblaser.tickettoride.ServerModel.UserModel.User loggedUser = DAO._SINGLETON.getUserByUserName(username);
            mapIdToUser(username, loggedUser);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static Boolean mapIdToUser(String username, com.example.ryanblaser.tickettoride.ServerModel.UserModel.User user){
        _M_idToUser.put(username, user);
        return true;
    }

    //________________________________________________________________________________________________________//




    //-----------------------------------------SETTERS AND GETTERS---------------------------------------------//

    public String get_Username() { return _S_username; }
    public void set_Username(String username) { _S_username = username; }

    public String get_Password(){ return _S_password; }
    public void set_Password(String password ) { _S_password = password; }

    public String get_Token(){ return _S_token; }
    public void set_Token(String token) { _S_token = token; }


    public Boolean isUserInGame() { return _B_isInGame; }
    public void set_UserGameStatus(Boolean _B_isInGame) { this._B_isInGame = _B_isInGame; }

    public List<Game> getJoinedGames() { return _L_joinedGames; }
    public void setJoinedGameList(List<Game> _L_joinedGames) { this._L_joinedGames = _L_joinedGames; }
    
	public static List<com.example.ryanblaser.tickettoride.ServerModel.UserModel.User> get_L_listOfAllUsers() { //TODO: Implement!
		return null;
	}


    //________________________________________________________________________________________________________//


    //------------------------------------------CLASS FUNCTIONS-----------------------------------------------//
    public void updateUserModel(){

    }

    public Boolean initializeGame(int gameId ){
        Game createdGame = DAO._SINGLETON.getGameFromId( gameId );
        Game.addGame(createdGame, gameId);
        addGameToJoinedGames(createdGame);
        return true;
    }

    public Boolean addGameToJoinedGames( Game game ){
        _L_joinedGames.add( game );
        return true;
    }

	public String get_S_token() {
		
		return _S_token;
	}



}
