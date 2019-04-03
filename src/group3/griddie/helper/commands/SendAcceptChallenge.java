package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkMain;

public class SendAcceptChallenge implements Command {
    //access for the whole class
    private NetworkMain runner;
    private int challengeNumber;

    //initialize the class
    public SendAcceptChallenge(NetworkMain runner, int challengeNumber) {
        this.runner = runner;
        this.challengeNumber = challengeNumber;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut(String.format("challenge accept %x", this.challengeNumber));
    }

    //set the challenge number
    public void setChallengeNumber(int challengeNumber) {
        this.challengeNumber = challengeNumber;
    }
}
