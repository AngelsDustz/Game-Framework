package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendAcceptChallenge implements Command {
    private NetworkRunner runner;
    private int challengeNumber;

    public SendAcceptChallenge(NetworkRunner runner, int challengeNumber){
        this.runner = runner;
        this.challengeNumber = challengeNumber;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut(String.format("challenge accept %x", this.challengeNumber));
    }

    public void setChallengeNumber(int challengeNumber) {
        this.challengeNumber = challengeNumber;
    }
}
