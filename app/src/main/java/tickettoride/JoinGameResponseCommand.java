package tickettoride;
class JoinGameResponseCommand implements ICommand{
  private final String gameId;
  public JoinGameResponseCommand(String g){
    gameId = g;}
  public void execute(){
    }}