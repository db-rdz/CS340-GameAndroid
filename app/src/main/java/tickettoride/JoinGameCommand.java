package tickettoride;
class JoinGameCommand implements ICommand{
  private final int authenticationCode;
  private final String gameId;
  public JoinGameCommand(int k, String g){
    authenticationCode = k;
    gameId = g;}
  public void execute(){
    }
  public int getAuthenticationCode(){
    return authenticationCode;}}