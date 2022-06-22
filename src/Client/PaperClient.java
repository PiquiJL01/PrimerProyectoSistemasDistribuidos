package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import Engine.Connection;
import Engine.Item;
import Engine.Smoker;
import Engine.Stand;

public class PaperClient extends Connection {

    public PaperClient() throws IOException {
        super("client");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        PaperClient client = new PaperClient();

        client.startClient();

    }


    public void startClient() throws IOException, ClassNotFoundException {

        List<Stand> list = new ArrayList<>();
        // Obtiene la lista de stand del servidor
        ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
        list = (ArrayList<Stand>) ois.readObject();
        
        Smoker papelSmoker = new Smoker(Item.papel, list);
        papelSmoker.start();

    }
    
}
