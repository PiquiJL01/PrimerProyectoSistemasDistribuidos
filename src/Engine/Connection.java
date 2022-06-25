package Engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private final int PORT = 2507;
    private final String HOST = "localhost";
    // protected String 
    protected ServerSocket ss;
    protected Socket cs;
    protected ObjectOutputStream outputObjectClient, outputObjectServer;
    protected ObjectInputStream inputObjectClient, inputObjectServer;
    
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
