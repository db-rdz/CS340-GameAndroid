package tickettoride.ICommand;
import tickettoride.Server.ServerFacade;
import tickettoride.UserInfo.User;
public class LoginCommand implements ICommand{
  private final User user;
  private LoginCommand(){}
  public LoginCommand(User u){
    user = u;}
  
  @Override
  public void execute(){
    ServerFacade.SINGLETON.login(user);}}
