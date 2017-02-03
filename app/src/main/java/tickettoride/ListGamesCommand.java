package tickettoride;
import java.util.Set;
class ListGamesCommand implements ICommand{
  private final Set<Game> games_list;
  public ListGamesCommand(Set<Game> list){
    games_list = list;}
  public void execute(){
    }}