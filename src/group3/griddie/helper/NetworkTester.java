package group3.griddie.helper;

public class NetworkTester {
    Thread thread = new Thread(new NetworkHelper());

    public static void main(String[] args){
        new NetworkTester().run();
    }

    public void run(){
        thread.start();
    }
}
