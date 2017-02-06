package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
import tickettoride.UserInfo.Username;
class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private final Username username;
  private final String str_game_id;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(Username u, String gameId){
    username = u;
    str_game_id = gameId;}

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addPlayer(username, str_game_id);}}
