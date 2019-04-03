package group3.griddie.helper;

public class NetworkHelper implements Runnable {
    //access for the class
    private NetworkRunner looper = new NetworkRunner();

    //run method
    @Override
    public void run() {
        looper.main();
    }

    //access to the network runner
    public NetworkRunner getNetworkRunner() {
        return looper;
    }
}
