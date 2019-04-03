package group3.griddie.helper;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Stack;

public class NetworkHelper implements Runnable {
    private NetworkRunner looper = new NetworkRunner();

    @Override
    public void run() {
        looper.main();
    }

    public NetworkRunner getNetworkRunner() {
        return looper;
    }
}
