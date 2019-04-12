package group3.griddie.network;

public class NetworkHelperRunner implements Runnable {

    private static final String IP = "localhost";
    private static final int PORT = 7789;

    public static NetworkHelperRunner instance;
    //access for the class
    private NetworkMain looper;

    public NetworkHelperRunner(){
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
