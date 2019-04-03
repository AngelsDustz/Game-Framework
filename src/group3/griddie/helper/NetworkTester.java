package group3.griddie.helper;

import group3.griddie.helper.commands.SendAcceptChallenge;
import group3.griddie.helper.commands.SendChallenge;
import group3.griddie.helper.commands.SendCommandLogin;
import group3.griddie.helper.invoker.CommandInvoker;

public class NetworkTester {
    NetworkHelper access = new NetworkHelper();
    NetworkHelper access2 = new NetworkHelper();
    Thread thread2 = new Thread(access2);
    Thread thread = new Thread(access);
    SendCommandLogin login2;
    SendCommandLogin login;
    CommandInvoker invoker;
    SendChallenge challenge;
    SendAcceptChallenge accept;

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
        thread2.start();
        try{
            Thread.sleep(5000);
        }

        catch (InterruptedException e){

        }
        invoker = new CommandInvoker();
        login = new SendCommandLogin(access.getNetworkRunner(), "jimmy");
        login2 = new SendCommandLogin(access2.getNetworkRunner(), "hi");
        challenge = new SendChallenge(access.getNetworkRunner(), "hi", "Tic-tac-toe");
        accept = new SendAcceptChallenge(access2.getNetworkRunner(), 0);

        invoker.executeCommand(login);
        try{
            Thread.sleep(5000);
        }

        catch (InterruptedException e){

        }
        invoker.executeCommand(login2);
        try{
            Thread.sleep(5000);
        }

        catch (InterruptedException e){

        }
        invoker.executeCommand(challenge);
        try{
            Thread.sleep(5000);
        }

        catch (InterruptedException e){

        }
        invoker.executeCommand(accept);
    }
}
