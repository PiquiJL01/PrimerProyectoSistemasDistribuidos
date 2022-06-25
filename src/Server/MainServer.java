package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Accion;
import Engine.Connection;

public class MainServer extends Connection {

    protected MainServer() throws IOException {
        super("server");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        MainServer server = new MainServer();
        System.out.println("Server started!");


    }

    public void startServer() {
        try {
            while(true) { 
                this.cs = this.ss.accept();
    
                this.outputObjectServer = new ObjectOutputStream(this.cs.getOutputStream());
                this.inputObjectServer = new ObjectInputStream(this.cs.getInputStream());
    
                // obtiene la accion de los clientes
                Accion accion = (Accion) this.inputObjectServer.readObject();
    
                Servidor threadServer = new Servidor(this.inputObjectServer, this.outputObjectServer);
                threadServer.start();
    
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
