package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import Engine.Connection;
import Engine.Item;
// import Engine.Smoker;
import Engine.Stand;

public class CigaretteClient extends Connection {

    protected CigaretteClient(String type) throws IOException {
        super("client");
    }

    public static void main(String[] args) {
        
        ThreadClient cigaretterCliente = new ThreadClient();

        try {
            while (true) {

            }
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
    
}
