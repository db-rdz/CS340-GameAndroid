package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
import tickettoride.Server.Game;
public class AddWaitingToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private final Game game;
  private AddWaitingToClientCommand(){}
  public AddWaitingToClientCommand(Game g){
    game = g;}

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addWaitingGame(game);}}
