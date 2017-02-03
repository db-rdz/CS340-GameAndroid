package tickettoride;
class CreateGameCommand implements ICommand{
  private final int authenticationCode;
  public CreateGameCommand(int k){
    authenticationCode = k;}
  public void execute(){
    }
  public int getAuthenticationCode(){
    return authenticationCode;}}