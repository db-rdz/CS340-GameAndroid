package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
import tickettoride.Server.Game;
public class AddJoinableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private final Game game;
  private AddJoinableToClientCommand(){}
  public AddJoinableToClientCommand(Game g){
    game = g;}

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addJoinableGame(game);}}
