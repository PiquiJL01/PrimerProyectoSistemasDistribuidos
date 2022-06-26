package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Accion;
import Engine.Connection;
import Engine.Item;
import Engine.Smoker;

public abstract class AbastractSmokerClient extends Connection implements Runnable {

    private Smoker smoker;

    protected AbastractSmokerClient(Item initialSmokerItem) throws IOException {
        super("client");
        this.smoker = new Smoker(initialSmokerItem);
    }


    @Override
    public void run() {
        try {
            
            Accion accion = smoker.getAction();
            outAction.writeObject(accion);

            if (accion.equals(Accion.buscar)) {
                Thread.sleep(2000);
                System.out.println("Enviando accion desde el cliente " + accion);

                outSmoker.writeObject(smoker);
                System.out.println("Guarde Item");

            }

            if (accion.equals(Accion.recibir)) {
                Thread.sleep(2000);
                System.out.println("Enviando accion desde el cliente " + accion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void getItemFromServer() throws ClassNotFoundException, IOException{
        Item ingredient = (Item) inItem.readObject();
        smoker.addMissingIngredient(ingredient);
    }


    public void runClient() {
        try {

            while (true) {

                this.outSmoker = new ObjectOutputStream(this.cs.getOutputStream());
                this.inSmoker = new ObjectInputStream(this.cs.getInputStream());
                this.outAction = new ObjectOutputStream(this.cs.getOutputStream());
                this.inAction = new ObjectInputStream(this.cs.getInputStream());
                this.outItem = new ObjectOutputStream(this.cs.getOutputStream());
                this.inItem = new ObjectInputStream(this.cs.getInputStream());
               

                Thread threadClient = new Thread(this);

                threadClient.start();
                threadClient.join();
            }
        } catch (IOException | InterruptedException  e) {
            e.printStackTrace();
        }
    }
    
}
