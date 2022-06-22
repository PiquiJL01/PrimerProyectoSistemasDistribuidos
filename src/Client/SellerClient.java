package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import Engine.Connection;
import Engine.Seller;
import Engine.Stand;

public class SellerClient extends Connection {

    protected SellerClient() throws IOException {
        super("client");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        SellerClient client = new SellerClient();

        client.startClient();

    }
    
    public void startClient() throws IOException, ClassNotFoundException {

        ArrayList<Stand> list = new ArrayList<>();
        // Obtiene la lista de stand del servidor
        ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
        list = (ArrayList<Stand>) ois.readObject();
        

        // tengo que generar una lista del stand y mandarla al servidor
        Seller seller = new Seller(list);

        Thread vamostilin = new Thread(seller);
        ((Thread) seller).start();

    }
    
    
}
