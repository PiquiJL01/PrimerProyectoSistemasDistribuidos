package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Engine.Accion;
// import Engine.Accion;
import Engine.Connection;
import Engine.Stand;

public class MainServer extends Connection {

    ArrayList<Stand> stands;    

    protected MainServer() throws IOException {
        super("server");

        stands = new ArrayList<>();
		
		Stand stand1 = new Stand(true);
		Stand stand2 = new Stand(true);
		Stand stand3 = new Stand(false);

		stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Server up!");
        
        MainServer server = new MainServer();
        server.startServer();


    }

    public void startServer() {
        try {
            while (true) { 
                
                this.cs = this.ss.accept();
    
                this.outSmoker = new ObjectOutputStream(this.cs.getOutputStream());
                this.inSmoker = new ObjectInputStream(this.cs.getInputStream());
                this.outAction = new ObjectOutputStream(this.cs.getOutputStream());
                this.inAction = new ObjectInputStream(this.cs.getInputStream());
                this.outItem = new ObjectOutputStream(this.cs.getOutputStream());
                this.inItem = new ObjectInputStream(this.cs.getInputStream());
    
                // obtiene la accion de los clientes
                // this.outServer.writeObject("Esperando por accion.. ");
                // Accion accion = (Accion) this.inAction.readObject();
    
                Servidor threadServer = new Servidor(stands, inSmoker, outSmoker, inAction, outAction,inItem, outItem);
                threadServer.start();
                // System.out.println("El cliente envia la accion" + accion );

            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
