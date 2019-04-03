package group3.griddie.helper;

public class NetworkTester {
    private String IP = "127.0.0.1";
    private int PORT = 7789;

    NetworkHelperThread access = new NetworkHelperThread(IP, PORT);
    Thread thread = new Thread(access);

    public static void main(String[] args) {
        new NetworkTester().run();
    }

    public void run() {
        thread.start();
    }
}
