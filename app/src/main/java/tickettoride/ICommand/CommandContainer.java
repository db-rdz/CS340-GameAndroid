package tickettoride.ICommand;
public class CommandContainer{
  public final String str_type;
  public final ICommand icommand;
  private CommandContainer(){}
  public CommandContainer(String type, ICommand cmd){
    str_type = type;
    icommand = cmd;}}
