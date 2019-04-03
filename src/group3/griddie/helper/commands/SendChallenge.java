package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendChallenge implements Command {
    //access for the whole class
    private NetworkRunner runner;
    private String nameEnemy;
    private String game;

    //initialize the class
    public SendChallenge(NetworkRunner runner, String nameEnemy, String game) {
        this.runner = runner;
        this.nameEnemy = nameEnemy;
        this.game = game;
    }

    //execute the command
    @Override
    public void execute() {
        String temp = String.format("challenge \"%s\" \"%s\"", this.nameEnemy, this.game);
        this.runner.putInBufferOut(temp);
    }

    //set the game name
    public void setGame(String game) {
        this.game = game;
    }

    //set the enemy name
    public void setNameEnemy(String nameEnemy) {
        this.nameEnemy = nameEnemy;
    }
}
