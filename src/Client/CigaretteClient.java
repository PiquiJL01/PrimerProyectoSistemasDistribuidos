package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Accion;
import Engine.Connection;
import Engine.Item;
import Engine.Smoker;

public class CigaretteClient extends Connection implements Runnable {

    private Smoker cigaretteSmoker;


    protected CigaretteClient() throws IOException {
        super("client");
        this.cigaretteSmoker = new Smoker(Item.tabaco);
    }


    public static void main(String[] args) {
        

        CigaretteClient client;
        try {
            client = new CigaretteClient();
            client.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

            //Accion action = (Accion) this.inputObjectClient.readObject();

            while (true) { 

                // Accion smokerAction = this.cigaretteSmoker.getAction();
                // this.outputObjectClient.writeObject(smokerAction);

                outputObjectClient.writeObject(this.cigaretteSmoker);


                // if (smokerAction != Accion.recibir) {
                //     outputObjectClient.writeObject(this.cigaretteSmoker);
                // } else {
                //     inputObjectClient.readObject();
                // }

                // switch (action) {
                //     case buscar:
                //         // buscara en el servidor un stand con el item que necesita (envia)
                //         if (smokerAction == Accion.buscar) {
                //             //envio al fumador al servidor para que busque 
                //             outputObjectClient.writeObject(this.cigaretteSmoker);
                //         }
                //         break;
                //     case fumar:
                //         //cuando tenga los items que necesita fumara (envia)
                //         if (smokerAction == Accion.fumar) {
                //             // envio al fumador al servidor para ponerlo en un banco a fumar
                //             outputObjectClient.writeObject(this.cigaretteSmoker);
                //         }
                //     break;
                //     case pedir:
                //         // si no ha encontrado los dos items en el servidor, pedira mas (envia)

                //         if (smokerAction == Accion.pedir) {
                //             this.cigaretteSmoker.orderNewIngredients();
                //         }
                //     break;
                //     case recibir:
                //         // recibira los items del servidor (recibe)
                //         if (smokerAction == Accion.recibir) {
                //             inputObjectClient.readObject();
                //         }
                //         break;
                //     default:
                //         break;
                // }

        }
        } catch (Exception e) {
         e.getStackTrace();
        }

        
    }
    
}
