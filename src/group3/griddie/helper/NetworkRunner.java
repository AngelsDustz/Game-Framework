package group3.griddie.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkRunner {

    private Client client = new Client();
    private HostCreator host = new HostCreator();

    private static final String IP = "127.0.0.1";
    private static final int PORT = 7789;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Boolean connectedrun;
    private BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    private Timer timer;
    private Timer timer1;

    //commands for sending to the server
    public enum Commands {
        LOGIN("login"),
        LOGOUT("logout"),
        SUBSCRIBE_REVERSI("subscribe Reversi"),
        SUBSCRIBE_TICTACTOE("subscribe Tic-tac-toe"),
        MOVE("move"),
        CHALLENGE("challenge"),
        CHALLENGE_ACCEPT("challenge accept"),
        GET("get"),
        FORFEIT("forfeit"),
        HELP("help");

        private final String text;

        Commands(final String text){
            this.text = text;
        }

        @Override
        public String toString(){
            return text;
        }
    }

    //init of the NetworkRunner
    public NetworkRunner(){
        socket = host.generateSocket(IP, PORT); //generates socket
        connectedrun = true; //if it can generate a socket sets it to true
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // makes input stream
            out = new PrintWriter(socket.getOutputStream(), true); //makes output stream
        }

        catch(IOException e){
            e.printStackTrace();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    putInBufferOut(scanner.readLine());
                } catch (IOException e) {

                }
            }
        }, 1000, 10);

        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                isConnected(); // checks if the server is online
            }
        }, 1000, 10000);
    }

    //checks the BufferReader and puts into the LinkedListQueue
    private void checkPutInBufferIn(){
        String input = null;
        try {
            if(in.ready()) {
                input = in.readLine();
                client.putInBufferIn(input);
            }
        }

        catch(IOException e){

        }
    }

    //reads the pops the top of the buffer
    public String readBufferIn(){
        return client.readBufferIn();
    }

    //puts a command in the buffer
    public void putInBufferOut(String command){
        client.sendCommand(command);
    }

    //sends the command that has been put into the buffer
    private void checkCommand(){
        if(client.bufferOut.size() > 0){
            out.println(client.readBufferOut());
        }
    }

    //checks if the server is reachable
    private void isConnected(){
        SocketAddress address = new InetSocketAddress(IP, PORT);
        Socket isConnectedSocket = new Socket();
        Boolean connected = true;
        try{
            isConnectedSocket.connect(address, 5000);
        }
        catch(IOException e){
            timer1.cancel();
            connected = false;
        }

        finally {
            try{
                isConnectedSocket.close();
            }

            catch (IOException e){
                connected = false;
            }
        }
        connectedrun = connected;
    }

    private void printAll(){
        while(client.bufferIn.size() > 0){
            System.out.println(readBufferIn());
        }
    }


    public void main(){
        //print the current connection
        System.out.println(socket);
        checkPutInBufferIn();
        printAll();
        while(connectedrun) {
            checkCommand(); //send the commands that are in the buffer
            checkPutInBufferIn(); // puts the commands from the socketbuffer in the linkedlistqueue
            printAll();
        }

        //prints out the socket closed if closed
        System.out.println("closed: " + socket);
        //closes everything in order
        out.close(); // closes the out bufferedReader
        try {
            socket.close(); // closes the Socket
            scanner.close();
            in.close(); // closes the Printwriter
            timer.cancel();
        } catch (IOException e) {
            e.printStackTrace(); //catches errors if any
        }


    }
}
