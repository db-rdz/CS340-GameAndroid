package tickettoride.ICommand;
import tickettoride.Server.ServerFacade;
import tickettoride.UserInfo.User;
public class RegisterCommand implements ICommand{
  private final User user;
  private RegisterCommand(){}
  public RegisterCommand(User u){
    user = u;}
  
  @Override
  public void execute(){
    ServerFacade.SINGLETON.register(user);}}
