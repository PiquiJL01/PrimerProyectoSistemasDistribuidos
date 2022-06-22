package Client;

import java.net.Socket;
import java.util.List;

import Engine.Item;
import Engine.Stand;

public class PaperClient extends ThreadClient {

    public PaperClient(Socket cs, List<Stand> stands) {
        super(cs, Item.papel, stands);
    }

    // @Override
    // public void run() {

    // }
    
}
