package tickettoride;
class LogoutCommand implements ICommand{
  private final int authenticationCode;
  public LogoutCommand(int k){
    authenticationCode = k;}
  public void execute(){
    }}