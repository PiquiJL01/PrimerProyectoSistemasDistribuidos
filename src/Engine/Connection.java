package Engine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private final int PORT = 2507;
    private final String HOST = "localhost";
    // protected String 
    protected ServerSocket ss;
    protected static Socket cs;
    protected DataOutputStream outputDataClient, outputDataServer;
    
    protected Connection (String type) throws IOException{
        if (type.equalsIgnoreCase("server")) {
            ss = new ServerSocket(PORT);
            cs = new Socket();
        }

        if (type.equalsIgnoreCase("client")) {
            cs = new Socket(HOST, PORT);
        }

    }

}
