package Engine;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
	private static final int PUERTO1 = 1100;
	private static final int PUERTO2 = 1101;
	private static final int PUERTO3 = 1103;
	
	

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Stand stand1 = new Stand();
		Stand stand2 = new Stand();
		Stand stand3 = new Stand();
		Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            @Override
            public boolean revisarStand(Item item) throws RemoteException{
            	return stand1.Revisar(item);
            }
            @Override
        	public void abastecer(int standNumber) throws RemoteException{
            	Item item = Item.vacio;
        		switch(standNumber) {
        			case 1:
        				item = stand1.abastecer();
        				break;
        			case 2:
        				item = stand2.abastecer();
        				break;
        			case 3:
        				item = stand3.abastecer();
        		}
        		LogWriter.mensajeALog(standNumber, Accion.abastecer, item);
        	}
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO1);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO1));
        registry.bind("LOG", remote);
        Registry registry2 = LocateRegistry.createRegistry(PUERTO2);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO2));
        registry2.bind("LOG", remote);
        Registry registry3 = LocateRegistry.createRegistry(PUERTO3);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO3));
        registry3.bind("LOG", remote);
	}	
}
