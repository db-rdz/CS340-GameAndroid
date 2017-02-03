package tickettoride;
class LoginCommand implements ICommand{
  private final Username username;
  private final Password password;
  public LoginCommand(Username u, Password p){
    username = u;
    password = p;}
  public void execute(){
    }}