package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Accion;
import Engine.Connection;
import Engine.Item;
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

    }

    public void runClient() {
        try {

            Thread threadClient = new Thread(this);
            threadClient.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void run() {
        
        try {
           
            this.outSmoker = new ObjectOutputStream(this.cs.getOutputStream());
            this.inSmoker = new ObjectInputStream(this.cs.getInputStream());
            this.outAction = new ObjectOutputStream(this.cs.getOutputStream());
            this.inAction = new ObjectInputStream(this.cs.getInputStream());
            this.outItem = new ObjectOutputStream(this.cs.getOutputStream());
            this.inItem = new ObjectInputStream(this.cs.getInputStream());

            while (true) {
                
                try {
                    
                    Accion accionFromServer = (Accion) inAction.readObject();


                    if (accionFromServer.equals(Accion.abastecer)) {
                        //epa necesito llenar el vicio manda ingredientes
                        outItem.writeObject(seller.selectItems());
                    }

                    if (accionFromServer.equals(Accion.pedir)) {
                        // el cliente no ha obtenido los items que necesita, manda mas
                        outItem.writeObject(seller.selectItems());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
