package Server;

import java.io.IOException;
// import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import Engine.*;


public class Servidor extends Thread  {
	protected ServerSocket serverSocket;
	protected Socket socket;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	private final Stand stand;
	private final StandNumber standNumber;
	
	public Servidor(StandNumber standNumber){
		this.standNumber = standNumber;
		this.stand = new Stand();
	}

	private void setConnection(StandNumber standNumber) throws IOException {
		int[] serverPorts = new int[] {2508, 2509, 2510};

		switch (standNumber){
			case Stand1:
				serverSocket = new ServerSocket(serverPorts[0]);
				break;
			case Stand2:
				serverSocket = new ServerSocket(serverPorts[1]);
				break;
			case Stand3:
				serverSocket = new ServerSocket(serverPorts[2]);
				break;
		}

		socket = new Socket();
	}


	@Override
	public void run() {
		try {
			int serverPORT1 = 2508;
			int serverPORT2 = 2509;
			int serverPORT3 = 2510;

			switch (standNumber){
				case Stand1:
					serverSocket = new ServerSocket(serverPORT1);
					break;
				case Stand2:
					serverSocket = new ServerSocket(serverPORT2);
					break;
				case Stand3:
					serverSocket = new ServerSocket(serverPORT3);
					break;
			}
			while (true){
				Writer.Write("Creando Conexion");
				socket = new Socket();
				Writer.Write("Conexion Creada");
				Writer.Write("Oyendo");
				this.socket = this.serverSocket.accept();
				Writer.Write("Conectados");
				this.inputStream = new ObjectInputStream(this.socket.getInputStream());
				this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
				String message = this.inputStream.readUTF();
				switch (Message.ReadAccion(message)){
					case "Buscar":
						boolean flag = this.stand.getIngredient(Message.ReadItem(message));
						if(flag){
							Writer.Write("Inviando Paquete");
							this.outputStream.writeBoolean(true);
						} else {
							this.outputStream.writeBoolean(false);
						}
						this.outputStream.flush();
						break;
					case "Abastecer":
						this.stand.refill();
						break;
				}
				outputStream.close();
				//socket.close();
				Writer.Write("Conexion Cerrada");
			}
		}
		catch (Exception e){
			Writer.Write(e.toString());
		}
	}
}
