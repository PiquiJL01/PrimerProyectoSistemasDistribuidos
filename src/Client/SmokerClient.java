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
            TimeUnit.SECONDS.sleep(1);
            try {
                Writer.Write("Buscando en el stand 1");
                this.SearchIngredient(StandNumber.Stand1);
                System.out.println("Buscando en stand: " );
            } catch (Exception e){
                try{
                    TimeUnit.SECONDS.sleep(1);
                    Writer.Write("Buscando en el stand 2");
                    this.SearchIngredient(StandNumber.Stand2);
                }catch (Exception e2){
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        Writer.Write("Buscando en el stand 3");
                        this.SearchIngredient(StandNumber.Stand3);
                    }catch (Exception e3){
                        TimeUnit.SECONDS.sleep(1);
                        Writer.Write("No se consiguio en los stands");
                        requestNumber += 1;
                        if (requestNumber == 2){
                            this.requestNewIngredients();
                            requestNumber = 0;
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
                Writer.Write("Buscando " + i.toString());
                try{
                    setConnection(standNumber);
                    Writer.Write("Enviando Mensaje");
                    outputStream.writeUTF(Message.Buscar + i.toString());
                    Writer.Write("Mensaje Enviado");
                    outputStream.flush();
                    Writer.Write("Leyendo Respuesta");
                    boolean inMessage = inputStream.readBoolean();
                    Writer.Write("Mensaje recibido");
                    outputStream.close();
                    socket.close();
                    if (inMessage){
                        Writer.Write("Ingrediente Conseguido");
                        this.Ingredients.replace(i, true);
                    }else {
                        Writer.Write("Ingrediente no Conseguido");
                        throw new Exception();
                    }
                }catch (IOException e){
                    Writer.Write("Error Buscando Ingrediente");
                }
            }
        }
    }

    private void setConnection(StandNumber standNumber) throws IOException {
        Writer.Write("Creando Conexion");
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
        Writer.Write("Conexion creada");
    }

    private void requestNewIngredients() throws IOException {
        Writer.Write("Solicitando Refrescar");
        int sellerPORT = 2511;
        socket = new Socket(HOST, sellerPORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeUTF(Message.Pedir);
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
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
