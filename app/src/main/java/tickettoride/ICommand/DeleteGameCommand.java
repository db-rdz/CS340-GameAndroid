package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
public class DeleteGameCommand implements ICommand{ // Joinable/Waiting/Resumable (Add*Game's take care of changed game type)
  private final String str_game_id;
  private DeleteGameCommand(){}
  public DeleteGameCommand(String gameId){
    str_game_id = gameId;}
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.removeGame(str_game_id);}}
