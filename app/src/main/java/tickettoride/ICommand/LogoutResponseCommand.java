package tickettoride.ICommand;
import tickettoride.Client.ClientFacade;
public class LogoutResponseCommand implements ICommand{
  
  @Override
  public void execute(){
    ClientFacade.SINGLETON.logoutSucceeded();}}
