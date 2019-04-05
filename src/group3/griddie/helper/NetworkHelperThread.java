package group3.griddie.helper;

public class NetworkHelperThread implements Runnable {
    //access for the class
    private NetworkMain looper;
    private final String IP;
    private final int PORT;

    public NetworkHelperThread(final String IP, final int PORT){
        this.IP = IP;
        this.PORT = PORT;
        this.looper = new NetworkMain(IP, PORT);
    }

    //run method
    @Override
    public void run() {
        this.looper.main();
    }

    //access to the network runner
    public NetworkMain getNetworkRunner() {
        return this.looper;
    }
}
