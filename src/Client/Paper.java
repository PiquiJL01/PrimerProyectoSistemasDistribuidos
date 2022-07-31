package Client;

import java.net.UnknownHostException;

import Engine.Item;
import Engine.Writer;

public class Paper extends SmokerClient {

    public Paper(Item item) throws UnknownHostException {
        super(item);
    }

    public static void main(String[] args){
        try{
            SmokerClient client = new Paper(Item.papel);
            client.run();
        }
        catch (Exception e){
            Writer.Write("Error mientras corre");
        }
    }

    
    
}
