package tickettoride.ICommand;
import java.util.List;
public class GetCommandsCommand implements ICommand{
  private final List<ICommand> list_icommands;
  private GetCommandsCommand(){}
  public GetCommandsCommand(List<ICommand> list){
    list_icommands = list;}
  
  @Override
  public void execute(){
    for(ICommand command : list_icommands)
      command.execute();}}
