package Server;

// import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Engine.*;


public class Servidor extends Thread  {
	protected ServerSocket serverSocket;
	protected Socket socket;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	private final Stand stand;
	private final StandNumber standNumber;
	
	public Servidor(Socket socket, StandNumber standNumber, Stand stand){
		this.socket = socket;
		this.standNumber = standNumber;
		this.stand = stand;
	}


	@Override
	public void run() {
		try {
			Writer.Write("Conectados");
			this.inputStream = new ObjectInputStream(this.socket.getInputStream());
			this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
			String message = this.inputStream.readUTF();

			switch (Message.ReadAccion(message)){
				case Message.Buscar:
					boolean flag = this.stand.getIngredient(Message.ReadItem(message));
					if(flag){
						Writer.Write("Enviando Paquete");
						this.outputStream.writeBoolean(true);
					} else {
						this.outputStream.writeBoolean(false);
					}
					this.outputStream.flush();
					break;
				case Message.Abastecer:
					this.stand.refill();
					break;
			}
			Writer.Write("Cerrando");
			outputStream.close();
			socket.close();
		}
		catch (Exception e){
			Writer.Write("Error: " + e.toString());
		}
		Writer.Write("Fin");
		return;
	}

	public Stand getStand() {
		return stand;
	}
}
