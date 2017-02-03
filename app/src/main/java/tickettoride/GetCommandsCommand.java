package tickettoride;
class GetCommandsCommand implements ICommand{
  private final int authenticationCode;
  public GetCommandsCommand(int k){
    authenticationCode = k;}
  public void execute(){
    }}