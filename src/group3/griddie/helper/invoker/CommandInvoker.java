package group3.griddie.helper.invoker;

import group3.griddie.helper.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    //list of commands in case of wanting it to do it again
    private final List<Command> commandOperations = new ArrayList<>();

    public void executeCommand(Command command){
        commandOperations.add(command);
        command.execute();
    }
}
