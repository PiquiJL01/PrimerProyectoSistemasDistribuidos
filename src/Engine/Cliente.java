package Engine;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
	private static final String IP = "localhost";
	private static final int PUERTO = 1100;
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("LOG");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String start = "\n\n------------------\n\nIngrese el mensaje a escribir en el Log: \n";
        do {
            System.out.println(start);
            
            interfaz.abastecer(1);
            
            System.out.println("Se ha escrito el mensaje en el Log");
        } while (true);
	}

}
