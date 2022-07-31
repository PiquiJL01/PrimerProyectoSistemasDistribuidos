package Client;

import java.net.UnknownHostException;

import Engine.Item;
import Engine.Writer;

public class Fosforo extends SmokerClient{

    public Fosforo(Item item) throws UnknownHostException {
        super(item);
       
    }

    public static void main(String[] args){
        try{
            SmokerClient client = new Fosforo(Item.fosforo);
            client.run();
        }
        catch (Exception e){
            Writer.Write("Error mientras corre");
        }
    }
    
}
