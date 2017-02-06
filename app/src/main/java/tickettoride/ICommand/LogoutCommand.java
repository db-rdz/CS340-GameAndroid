package tickettoride.ICommand;
import tickettoride.Server.ServerFacade;
public class LogoutCommand implements ICommand{
  private final int int_authentication_code;
  private LogoutCommand(){}
  public LogoutCommand(int k){
    int_authentication_code = k;}
  
  @Override
  public void execute(){
    ServerFacade.SINGLETON.logout(int_authentication_code);}
  public int getAuthenticationCode(){
    return int_authentication_code;}}
