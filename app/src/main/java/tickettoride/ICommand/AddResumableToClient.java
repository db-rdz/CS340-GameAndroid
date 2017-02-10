package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
import tickettoride.Server.Game;
public class AddResumableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private final Game game;
  private AddResumableToClientCommand(){}
  public AddResumableToClientCommand(Game g){
    game = g;}

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addResumableGame(game);}}
