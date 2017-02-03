package tickettoride;
class LoginRegisterResponseCommand implements ICommand{
  private final Username username;
  private final Password password;
  private final int authenticationCode;
  public LoginRegisterResponseCommand(Username u, Password p, int k){
    username = u;
    password = p;
    authenticationCode = k;}
  public void execute(){
    }
  public int getAuthenticationCode(){
    return authenticationCode;}}