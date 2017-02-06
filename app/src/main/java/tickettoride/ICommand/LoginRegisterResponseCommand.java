package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
import tickettoride.UserInfo.User;
public class LoginRegisterResponseCommand implements ICommand{
  private final User user;
  private final int int_authentication_code;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(User u, int k){
    user = u;
    int_authentication_code = k;}
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user, int_authentication_code);}
  public int getAuthenticationCode(){
    return int_authentication_code;}}
