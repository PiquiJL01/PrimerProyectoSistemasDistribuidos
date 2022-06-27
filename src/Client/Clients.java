package Client;

import java.net.UnknownHostException;

import Engine.Item;
import Engine.Writer;

public class Clients extends SmokerClient {

    public Clients(Item item) throws UnknownHostException {
        super(item);
    }

    public static void main(String[] args){
        try{
            SmokerClient client = new Clients(Item.papel);
            client.run();
        }
        catch (Exception e){
            Writer.Write("Error mientras corre");
        }
    }

    
    
}
