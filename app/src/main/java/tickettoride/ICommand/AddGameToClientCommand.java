package tickettoride.ICommand;
import tickettoride.Server.Game;
public class AddGameToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private final Game game;
  private AddGameToClientCommand(){}
  public AddGameToClientCommand(Game g){
    game = g;}

  @Override
  public void execute(){
    ClientFacade.SINGLETON.addGame(game);}}
