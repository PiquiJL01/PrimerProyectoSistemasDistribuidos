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
		socket = new Socket();
	}

	@Override
	public void run() {
		try {
			while (true){
				setConnection(standNumber);
				this.socket = this.serverSocket.accept();
				this.inputStream = new ObjectInputStream(socket.getInputStream());
				Writer.Write(this.inputStream.readUTF());

				String message = this.inputStream.readUTF();
				switch (Objects.requireNonNull(Message.ReadAccion(message))){
					case buscar:
						if(this.stand.getIngredient(Message.ReadItem(message))){
							this.outputStream.writeUTF(Message.Send(Accion.recibir));
						} else {
							this.outputStream.writeUTF(Message.SendError());
						}
						this.outputStream.flush();
						break;
					case abastecer:
						this.stand.refill();
						break;
				}
				outputStream.close();
				socket.close();
			}
		}
		catch (Exception e){
			Writer.Write(e.toString());
		}
	}
}
