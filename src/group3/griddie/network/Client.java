package group3.griddie.network;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    //access for the class and other class
    public volatile ArrayBlockingQueue<String> bufferIn;
    public volatile ArrayBlockingQueue<String> bufferOut;

    //initialize the client
    public Client() {
        bufferIn = new ArrayBlockingQueue<>(10);
        bufferOut = new ArrayBlockingQueue<>(10);
    }

    //puts the command in the bufferOut
    public void sendCommand(String command) {
        try {
            bufferOut.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //peek the bufferOut
    public String peekBufferOut() {
        String peek = bufferOut.peek();
        return peek;
    }

    //reads the bufferOut and deletes the value from the queue
    public String readBufferOut() {
        String peek = null;
        if (bufferOut.size() > 0) {
            peek = bufferOut.poll();
        }
        return peek;
    }

    //peek the bufferIn
    public String peekBufferIn() {
        String peek = bufferIn.peek();
        return peek;
    }

    //read the bufferIn en removes the value from the queue
    public String readBufferIn() {
        String peek = null;
        if (bufferIn.size() > 0) {
            peek = bufferIn.poll();
        }
        return peek;
    }

    //put the value in the bufferIn
    public void putInBufferIn(String incoming) {
        //System.out.println(incoming);
        try {
            bufferIn.put(incoming);
        } catch (InterruptedException e) {

        }
    }
}
