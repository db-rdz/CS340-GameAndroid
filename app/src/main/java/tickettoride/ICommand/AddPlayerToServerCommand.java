package tickettoride.ICommand;
import tickettoride.Server.ServerFacade;
public class AddPlayerToServerCommand implements ICommand{
  private final String str_game_id;
  private final int int_authentication_code;
  private AddPlayerToServerCommand(){}
  public AddPlayerToServerCommand(String gameId, int k){
    str_game_id = gameId;
    int_authentication_code = k;}

  @Override
  public void execute(){ // auth key must be changed to Username somewhere
    ServerFacade.SINGLETON.addPlayer(int_authentication_code, str_game_id)}
  public int getAuthenticationCode(){
    return int_authentication_code;}}
