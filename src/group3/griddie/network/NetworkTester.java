package group3.griddie.network;

import group3.griddie.network.commands.GetAllInformation;
import group3.griddie.network.commands.GetInformation;
import group3.griddie.network.commands.SendCommandLogin;
import group3.griddie.network.commands.SendCommandSubscribe;
import group3.griddie.network.invoker.CommandInvoker;

import java.util.LinkedList;

public class NetworkTester {
    private String IP = "127.0.0.1";
    private int PORT = 7789;

    NetworkHelperThread access = new NetworkHelperThread(IP, PORT);
    SendCommandLogin login = new SendCommandLogin(access.getNetworkRunner(), "offline");
    SendCommandSubscribe subscribe = new SendCommandSubscribe(access.getNetworkRunner(), "Tic-tac-toe");
    GetAllInformation allInformation = new GetAllInformation(access.getNetworkRunner());
    GetInformation information = new GetInformation(access.getNetworkRunner());
    CommandInvoker invoker = new CommandInvoker();
    Thread thread = new Thread(access);

    public static void main(String[] args) {
        new NetworkTester().run();
    }

    public void run() {
        thread.start();
        while(access.getNetworkRunner().ready == false){
            try{
                Thread.sleep(1000);
            }

            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        invoker.executeCommand(login);
        invoker.executeCommand(subscribe);
    }
}

