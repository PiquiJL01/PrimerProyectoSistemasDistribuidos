package Client;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Engine.Item;
import Engine.Seller;
import Engine.Smoker;
import Engine.Stand;

public class ThreadClient extends Thread implements IThreadClient {
    
    private Smoker smoker;
    private Socket socket;
    private Seller seller;

    public ThreadClient(Socket cs, Item item, List<Stand> standList) {

        this.smoker = new Smoker(item,standList);
        this.socket = cs;
    }

    public ThreadClient(Socket cs, ArrayList<Stand> standList) {
        this.seller = new Seller(standList);
    }

    @Override
    public void run() {
        this.run();
    }
}
