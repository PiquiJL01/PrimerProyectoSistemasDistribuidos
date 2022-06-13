package Engine;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interfaz extends Remote {
	boolean revisarStand(Item item) throws RemoteException;
	void abastecer(int standNumber) throws RemoteException;
}
