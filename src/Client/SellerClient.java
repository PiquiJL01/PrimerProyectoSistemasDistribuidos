package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Engine.Connection;
import Engine.Seller;
import Engine.Stand;

public class SellerClient extends Connection implements Runnable {

    private Seller seller;
    private List<Stand> stands;

    protected SellerClient() throws IOException {
        super("client");
        this.stands = new ArrayList<Stand>();
        // this.seller = new Seller(this.stands);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        

        Runnable client = new SellerClient();

        Thread threadSeller = new Thread(client);

        threadSeller.start();
        // client.start();

    }
    

    @Override
    public void run() {
        
        try {
            // Obtiene la lista de stand del servidor
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
            stands.addAll((ArrayList<Stand>) ois.readObject());
            this.seller = new Seller(stands);
            
            System.out.println("Pendiente pa enviar la mota");
            
            seller.start();
            System.out.println("Si imprimo esto es porque no estoy pegao");
            
            while(true) { 
                
                // mientras el vendedor este corriendo va a estar enviando items al servidor

                ObjectOutputStream oss = new ObjectOutputStream(cs.getOutputStream());
                oss.writeObject(seller.selectItems());
            

            // Thread vamostilin = new Thread(seller)


            // ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream());
            // oos.writeObject(oos);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    
}
