package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendChallenge implements Command {
    private NetworkRunner runner;
    private String nameEnemy;
    private String game;

    public SendChallenge(NetworkRunner runner, String nameEnemy, String game){
        this.runner = runner;
        this.nameEnemy = nameEnemy;
        this.game = game;
    }

    @Override
    public void execute() {
        String temp = String.format("challenge \"%s\" \"%s\"", this.nameEnemy, this.game);
        this.runner.putInBufferOut(temp);
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setNameEnemy(String nameEnemy) {
        this.nameEnemy = nameEnemy;
    }
}
