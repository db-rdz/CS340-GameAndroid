package tickettoride;
class RegisterCommand implements ICommand{
  private final Username username;
  private final Password password;
  public RegisterCommand(Username u, Password p){
    username = u;
    password = p;}
  public void execute(){
    }}