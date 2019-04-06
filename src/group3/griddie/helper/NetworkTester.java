package group3.griddie.helper;

import group3.griddie.helper.commands.SendCommandLogin;
import group3.griddie.helper.commands.SendCommandSubscribe;
import group3.griddie.helper.invoker.CommandInvoker;

public class NetworkTester {
    private String IP = "134.209.93.232";
    private int PORT = 7789;

    NetworkHelperThread access = new NetworkHelperThread(IP, PORT);
    SendCommandLogin login = new SendCommandLogin(access.getNetworkRunner(), "offline");
    SendCommandSubscribe subscribe = new SendCommandSubscribe(access.getNetworkRunner(), "Tic-tac-toe");
    CommandInvoker invoker = new CommandInvoker();
    Thread thread = new Thread(access);

    public static void main(String[] args) {
        new NetworkTester().run();
    }

    public void run() {
        thread.start();
        invoker.executeCommand(login);
        invoker.executeCommand(subscribe);
    }
}

