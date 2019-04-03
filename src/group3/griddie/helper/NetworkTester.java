package group3.griddie.helper;

import group3.griddie.helper.commands.SendChallenge;
import group3.griddie.helper.commands.SendCommandLogin;
import group3.griddie.helper.invoker.CommandInvoker;

public class NetworkTester {
    NetworkHelper access = new NetworkHelper();
    Thread thread = new Thread(access);
    SendCommandLogin login;
    CommandInvoker invoker;
    SendChallenge challenge;

    public static void main(String[] args) {
        new NetworkTester().run();
    }

    public void run() {
        thread.start();
        try{
            Thread.sleep(5000);
        }

        catch (InterruptedException e){

        }
        invoker = new CommandInvoker();
        login = new SendCommandLogin(access.getNetworkRunner(), "jimmy");
        challenge = new SendChallenge(access.getNetworkRunner(), "hi", "Tic-tac-toe");
        invoker.executeCommand(login);
        invoker.executeCommand(challenge);
    }
}
