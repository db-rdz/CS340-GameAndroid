package tickettoride.ICommand;
import java.util.Set;
import tickettoride.Client.ClientFacade;
import tickettoride.Server.Game;
public class ListJoinableCommand implements ICommand{ // sent to clients after login/registration
  private final Set<Game> set_game_list;
  private ListJoinableCommand(){}
  public ListJoinableCommand(Set<Game> list){
    set_game_list = list;}
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.listJoinableGames(set_game_list);}}
