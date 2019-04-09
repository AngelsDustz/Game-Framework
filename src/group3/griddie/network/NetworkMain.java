package group3.griddie.network;

import group3.griddie.network.producer.HostCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkMain {
    //setups the IP adress en PORT
    private final String IP;
    private final int PORT;
    //variables access for the class only
    //creates a client and a hostcreator
    private Client client = new Client();
    private HostCreator host = new HostCreator();
    //variables the streams for input and output
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Boolean connectedrun;
    private Timer timer;
    public volatile boolean ready = false;


    //init of the NetworkMain
    public NetworkMain(final String IP, final int PORT) {
        this.IP = IP;
        this.PORT = PORT;
        socket = host.generateSocket(this.IP, this.PORT); //generates socket
        connectedrun = true; //if it can generate a socket sets it to true
        setupStreams();//for the input
        setupStartTimer();
    }

    public Client getClient() {
        return client;
    }

    //setups the timer
    private void setupStartTimer() {
        //schedules a method
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                isConnected(); // checks if the server is online
            }
        }, 1000, 10000);
    }

    public int getBufferInSize(){
        int size = client.bufferIn.size();
        return size;
    }

    //sets up the stream
    private void setupStreams() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // makes input stream
            out = new PrintWriter(socket.getOutputStream(), true); //makes output stream
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //checks the BufferReader and puts into the LinkedListQueue
    private void checkPutInBufferIn() {
        String input = null;
        try {
            if (in.ready()) {
                input = in.readLine();
                client.putInBufferIn(input);
            }
        } catch (IOException e) {

        }
    }

    //reads the pops the top of the buffer
    public synchronized String readBufferIn() {
        return client.readBufferIn();
    }

    //puts a command in the buffer
    public void putInBufferOut(String command) {
        client.sendCommand(command);
    }

    //sends the command that has been put into the buffer
    private void checkCommand() {
        if (client.bufferOut.size() > 0) {
            out.println(client.readBufferOut());
        }
    }

    //checks if the server is reachable
    private void isConnected() {
        SocketAddress address = new InetSocketAddress(this.IP, this.PORT);
        Socket isConnectedSocket = new Socket();
        Boolean connected = true;
        try {
            isConnectedSocket.connect(address, 5000);
        } catch (IOException e) {
            timer.cancel();
            connected = false;
        } finally {
            try {
                isConnectedSocket.close();
            } catch (IOException e) {
                connected = false;
            }
        }
        connectedrun = connected;
    }

    //returns linked list if you want all the returns for servers
    public ArrayList<String> printAll() {
        ArrayList<String> buffer = new ArrayList<>();
        while (client.bufferIn.size() > 0) {
            buffer.add(readBufferIn());
        }
        return buffer;
    }

    public void printEverything(){
        ArrayList<String> buffer = new ArrayList<>();
        while (client.bufferIn.size() > 0) {
            System.out.println(readBufferIn());
        }
    }

    public void setConnectedrun(Boolean connectedrun) {
        this.connectedrun = connectedrun;
    }

    public void main() {
        //print the current connection
        System.out.println("opened: " + socket);
        System.out.println("Buffer size: " + client.bufferIn.size());
        checkPutInBufferIn(); // puts the commands from the socketbuffer in the linkedlistqueue
        while (connectedrun) {
            checkCommand(); //send the commands that are in the buffer
            checkPutInBufferIn(); // puts the commands from the socketbuffer in the linkedlistqueue
            //printEverything();
            ready = true;
        }

        //prints out the socket closed if closed
        System.out.println("closed: " + socket);
        //closes everything in order
        out.close(); // closes the out bufferedReader
        try {
            socket.close(); // closes the Socket
            in.close(); // closes the Printwriter
        } catch (IOException e) {
            e.printStackTrace(); //catches errors if any
        }


    }
}
