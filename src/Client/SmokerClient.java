package Client;

import Engine.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// import java.util.concurrent.TimeUnit;

public abstract class SmokerClient {
    //private final String HOST = "25.13.191.185";
    private final String HOST = "localhost";
    // private final String HOST;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;
    private final Map<Item, Boolean> Ingredients;
    private final Item infiniteItem;
    private LogWriter log;
    private int requestNumber;
    private String messageToSave = "";

    public SmokerClient(Item item) throws UnknownHostException {
        Ingredients = new HashMap<>();
        infiniteItem = item;
        for (Item i:
             Item.Items) {
            if(i == infiniteItem){
                Ingredients.put(i, true);
            }else{
                Ingredients.put(i, false);
            }
        }
        Writer.Write("Fumador [" + infiniteItem + "] tiene " + Ingredients);
        requestNumber = 0;
        log = new LogWriter();
    }

    public void run() throws Exception {
        
        while(true){

            // Thread.sleep(2000);
            TimeUnit.SECONDS.sleep(2);
            try {

                messageToSave = "fumador[" + infiniteItem + "]" + " - accion[buscando] - intento: [" + (requestNumber+1) +"]";
                System.out.println(messageToSave);
                log.saveMessageOnFile(messageToSave);

                this.SearchIngredient();
            } catch (Exception e){
                TimeUnit.SECONDS.sleep(2);
                Writer.Write("No se consiguio en el stand");

                this.requestNumber += 1;
                if (this.requestNumber == 2){
                    String messageToSave = "fumador[" + infiniteItem + "]" + " - accion[pidiendo] ";
                    log.saveMessageOnFile(messageToSave);
                    this.requestNewIngredients();
                    this.requestNumber = 0;
                }
            }
            this.Smoke();
        }
    }

    public void SearchIngredient() throws Exception{
        int serverPORT = 2508;

        for (Item i: Item.Items) {
            if(!this.Ingredients.get(i)){
                System.out.println(Ingredients.get(i));
                Writer.Write("Buscando [" + i.toString() + "]");
                Thread.sleep(500);
                try{
                    socket = new Socket(HOST, serverPORT);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Thread.sleep(500);
                    outputStream.writeUTF(Message.Buscar + i.toString());
                    // Writer.Write("Mensaje Enviado");
                    Thread.sleep(500);
                    outputStream.flush();
                    // Writer.Write("Leyendo Respuesta");
                    Thread.sleep(500);
                    boolean inMessage = inputStream.readBoolean();
                    // Writer.Write("Mensaje recibido");
                    Thread.sleep(2000);
                    outputStream.close();
                    socket.close();
                    if (inMessage){
                        Writer.Write("Ingrediente Conseguido");
                        Thread.sleep(500);
                        this.Ingredients.replace(i, true);
                    }else {
                        Writer.Write("Ingrediente no Conseguido");
                        // Thread.sleep(500);
    
                        //throw new Exception();
                    }
                }catch (IOException e){
                    Writer.Write("Error Buscando Ingrediente");
                }
                Thread.sleep(2000);
            }
        }
    }

    private void setConnection(StandNumber standNumber) throws IOException {

    }

    private synchronized void requestNewIngredients() throws IOException {
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

    public void Smoke() throws InterruptedException, IOException {
        Writer.Write("Intentando Fumar");
        Thread.sleep(500);
        boolean canSmoke = true;
        for (Item i:
             Item.Items) {
            if(!Ingredients.get(i)){
                canSmoke = false;
            }
        }
        System.out.println("Fumador [" + infiniteItem + "] tiene " + Ingredients);
        if(canSmoke){
            for (Item i:
                 Item.Items) {
               if(i != infiniteItem){
                   Ingredients.replace(i, false);
               }
            }
            String messageToSend = "fumador[" + this.infiniteItem + "]" + " - accion[fumando] - intento: [" + this.requestNumber+"] ";
            Writer.Write("Fumando");
            log.saveMessageOnFile(messageToSend);
            // TimeUnit.SECONDS.sleep(5);
            Thread.sleep(5000);
            Writer.Write("Termina de Fumar");

        }
    }
}
