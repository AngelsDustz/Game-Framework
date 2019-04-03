package group3.griddie.helper;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    public LinkedBlockingQueue<String> bufferIn;
    public LinkedBlockingQueue<String> bufferOut;

    public Client() {
        bufferIn = new LinkedBlockingQueue<>();
        bufferOut = new LinkedBlockingQueue<>();
    }

    public void sendCommand(String command) {
        try {
            bufferOut.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String peekBufferOut() {
        String peek = bufferOut.peek();
        return peek;
    }

    public String readBufferOut() {
        String peek = null;
        if (bufferOut.size() > 0) {
            peek = bufferOut.peek();
            bufferOut.poll();
        }
        return peek;
    }

    public String peekBufferIn() {
        String peek = bufferIn.peek();
        return peek;
    }

    public String readBufferIn() {
        String peek = null;
        while (bufferIn.size() > 0) {
            peek = bufferIn.peek();
            bufferIn.poll();
        }
        return peek;
    }

    public void putInBufferIn(String command) {
        try {
            bufferIn.put(command);
        } catch (InterruptedException e) {

        }
    }

}
