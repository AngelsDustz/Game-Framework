package group3.griddie.helper;

public class NetworkTester {
    NetworkHelper access = new NetworkHelper();
    Thread thread = new Thread(access);

    public static void main(String[] args) {
        new NetworkTester().run();
    }

    public void run() {
        thread.start();
    }
}
