package Client;

import java.net.Socket;
import java.util.List;

import Engine.Item;
import Engine.Stand;

public class MatchClient extends ThreadClient {

    public MatchClient(Socket cs, List<Stand> stands) {
        super(cs, Item.fosforo, stands);

    }

}