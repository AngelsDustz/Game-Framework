package group3.griddie.game.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ServerResponse {

    public interface Action {
        void execute(Map<String, String> data);
    }

    private String command;
    private ArrayList<ServerResponse> subs;
    private Action action;

    public ServerResponse(String command) {
        this.command = command;
        subs = new ArrayList<>();
    }

    public ServerResponse(String command, Action action) {
        this.command = command;
        subs = new ArrayList<>();
        this.action = action;
    }

    public void addSub(ServerResponse sub) {
        subs.add(sub);
    }

    public ArrayList<ServerResponse> getSubs() {
        return subs;
    }

    public String getCommand() {
        return command;
    }

    public void excecute(String data) {
        if (this.action != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> converted = gson.fromJson(data, type);

            action.execute(converted);
        }
    }

}
