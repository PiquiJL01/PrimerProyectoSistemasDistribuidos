package Client;

import Engine.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SmokerClient {
    private final String HOST = "localhost";
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;
    private final Map<Item, Boolean> Ingredients;
    private final Item infiniteItem;

    public SmokerClient() {
        Ingredients = new HashMap<>();
        infiniteItem = Item.randomItem();
        for (Item i:
             Item.Items) {
            if(i == infiniteItem){
                Ingredients.put(i, true);
            }else{
                Ingredients.put(i, false);
            }
        }
    }

    public static void main(String[] args){
        try{
            SmokerClient client = new SmokerClient();
            client.run();
        }
        catch (Exception e){
            Writer.Write("Error mientras corre");
        }
    }

    public void run() throws Exception {
        int requestNumber = 0;
        while(true){
            try {
                this.SearchIngredient(StandNumber.Stand1);
                System.out.println("Buscando en stand: " );
            } catch (Exception e){
                try{
                    this.SearchIngredient(StandNumber.Stand2);
                }catch (Exception e2){
                    try{
                        this.SearchIngredient(StandNumber.Stand3);
                    }catch (Exception e3){
                        requestNumber += 1;
                        if (requestNumber == 2){
                            this.requestNewIngredients();
                        }
                    }
                }
            }
            this.Smoke();
        }
    }

    public void SearchIngredient(StandNumber standNumber) throws Exception{
        Writer.Write("Buscando ingrediente");
        for (Item i:
             Item.Items) {
            if(!this.Ingredients.get(i)){
                try{
                    setConnection(standNumber);
                    outputStream.writeUTF(Message.Send(Accion.buscar, i));
                    outputStream.flush();
                    String message = inputStream.readUTF();
                    outputStream.close();
                    socket.close();
                    if (Message.ReadAccion(message) == Accion.recibir){
                        this.Ingredients.replace(i, true);
                    }else {
                        throw new Exception();
                    }
                }catch (IOException ignored){ }
            }
        }
    }

    private void setConnection(StandNumber standNumber) throws IOException {
        int serverPORT1 = 2508;
        int serverPORT2 = 2509;
        int serverPORT3 = 2510;
        switch (standNumber){
            case Stand1:
                socket = new Socket(HOST, serverPORT1);
                break;
            case Stand2:
                socket = new Socket(HOST, serverPORT2);
                break;
            case Stand3:
                socket = new Socket(HOST, serverPORT3);
                break;
        }
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    private void requestNewIngredients() throws IOException {
        int sellerPORT = 2511;
        socket = new Socket(HOST, sellerPORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeUTF(Message.Send(Accion.pedir));
        outputStream.flush();
        outputStream.close();
        socket.close();
    }

    public void Smoke() throws InterruptedException {
        Writer.Write("Intentando Fumar");
        boolean canSmoke = true;
        for (Item i:
             Item.Items) {
            if(!Ingredients.get(i)){
                canSmoke = false;
            }
        }
        if(canSmoke){
            for (Item i:
                 Item.Items) {
               if(i != infiniteItem){
                   Ingredients.replace(i, false);
               }
            }
            Writer.Write("Fumando");
            Thread.sleep(200);
        }
    }
}
