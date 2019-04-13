package group3.griddie.game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import group3.griddie.game.player.OnlinePlayer;
import group3.griddie.game.player.Player;
import group3.griddie.util.event.ArgEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class Communication {

    public interface Action {
        void doAction(Map<String, String> data);
    }

    private class Command {

        private String command;
        private Action action;
        private ArrayList<Command> subCommands;

        public Command(String command) {
            this(command, null);
        }

        public Command(String command, Action action) {
            this.command = command;
            this.action = action;
            subCommands = new ArrayList<>();
        }

        public void addSubCommand(Command subCommand) {
            this.subCommands.add(subCommand);
        }

        public Command findSubCommand(String command) {
            for (Command subCommand : subCommands) {
                if (subCommand.command.equals(command)) {
                    return subCommand;
                }
            }

            return null;
        }

        public void execute(String data) {
            if (action != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                Map<String, String> converted = gson.fromJson(data, type);
                action.doAction(converted);
            }
        }
    }

    private Command rootCommand;
    private Game game;

    public Communication(Game game) {
        this.game = game;

        rootCommand = new Command("");
        Command svrCommand = new Command("SVR");

        Command gameCommand = new Command("GAME");

        Command matchCommand = new Command("MATCH", (data) -> {
            OnlinePlayer onlinePlayer = new OnlinePlayer(data.get("OPPONENT"));
            game.getLobby().join(onlinePlayer);

            Player startPlayer = game.getLobby().getPlayer(data.get("PLAYERTOMOVE"));
            game.setActivePlayer(startPlayer);
        });

        Command moveCommand = new Command("MOVE", (data) -> { });
        Command yourTurnCommand = new Command("YOURTURN", (data) -> {

        });

        rootCommand.addSubCommand(svrCommand);
        svrCommand.addSubCommand(gameCommand);
        gameCommand.addSubCommand(matchCommand);
        gameCommand.addSubCommand(yourTurnCommand);
        gameCommand.addSubCommand(moveCommand);
    }

    public void handle(String input) {
        String[] split = input.split(" ");

        Command current = rootCommand;
        for (int i = 0; i < split.length; i++) {
            Command subCommand = current.findSubCommand(split[i]);
            if (subCommand != null) {
                current = subCommand;

                StringBuilder data = new StringBuilder();
                for (int j = i + 1; j < split.length; j++) {
                    data.append(split[j]).append(" ");
                }

                current.execute(data.toString());
            }
        }
    }

}
