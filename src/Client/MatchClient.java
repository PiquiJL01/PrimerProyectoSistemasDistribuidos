package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import Engine.Connection;
import Engine.Item;
import Engine.Smoker;
import Engine.Stand;

public class MatchClient extends Connection {

    public MatchClient() throws IOException {
        super("client");

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        MatchClient client = new MatchClient();

        client.startClient();

    }


    public void startClient() throws IOException, ClassNotFoundException {

        List<Stand> list = new ArrayList<>();
        // Obtiene la lista de stand del servidor
        ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
        list = (ArrayList<Stand>) ois.readObject();
        
        Smoker matchSmoker = new Smoker(Item.fosforo, list);
        matchSmoker.start();

    }

}