package group3.griddie.helper.producer;

import java.io.IOException;
import java.net.Socket;

public class HostCreator {
    private Socket socket;

    //checks if the host is available
    private Boolean hostAvailabilityCheck(String IP, int PORT) {
        try (Socket s = new Socket(IP, PORT)) {
            return true;
        } catch (IOException e) {

        }
        return false;
    }

    //generates the socket recursively
    public Socket generateSocket(String IP, int PORT) {
        Socket generatedSocket = null;
        if (hostAvailabilityCheck(IP, PORT)) {
            try {
                generatedSocket = new Socket(IP, PORT);
                return generatedSocket;
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return generateSocket(IP, PORT);
    }

    //get the last made socket
    public Socket getSocket() {
        return socket;
    }
}
