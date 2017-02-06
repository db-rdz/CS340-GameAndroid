package tickettoride.ICommand;
import java.util.Set;
import tickettoride.Client.ClientFacade;
import tickettoride.Server.Game;
public class ListWaitingCommand implements ICommand{ // sent to clients after login/registration
  private final Set<Game> set_game_list;
  private ListWaitingCommand(){}
  public ListWaitingCommand(Set<Game> list){
    set_game_list = list;}
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.listWaitingGames(set_game_list);}}
