package Client;

import java.net.UnknownHostException;

import Engine.Item;
import Engine.Writer;

public class Tabacco extends SmokerClient{

    public Tabacco(Item item) throws UnknownHostException {
        super(item);
    }

    public static void main(String[] args){
        try{
            SmokerClient client = new Tabacco(Item.tabaco);
            client.run();
        }
        catch (Exception e){
            Writer.Write("Error mientras corre");
        }
    }
    
}
