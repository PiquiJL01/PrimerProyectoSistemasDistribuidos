package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Connection;
import Engine.Seller;

public class SellerClient extends Connection implements Runnable {

    private Seller seller;

    protected SellerClient() throws IOException {
        super("client");
        this.seller = new Seller();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        try {
            SellerClient sellerClient = new SellerClient();
            sellerClient.runClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // client.start();

    }

    public void runClient() {
        try {

            this.inputObjectClient = new ObjectInputStream(this.cs.getInputStream());
            this.outputObjectClient = new ObjectOutputStream(this.cs.getOutputStream());

            Thread threadClient = new Thread(this);
            threadClient.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void run() {
        
        try {
           
            this.outputObjectClient = new ObjectOutputStream(this.cs.getOutputStream());
            this.inputObjectClient = new ObjectInputStream(this.cs.getInputStream());

            while (true) {
                outputObjectClient.writeObject(this.seller);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
