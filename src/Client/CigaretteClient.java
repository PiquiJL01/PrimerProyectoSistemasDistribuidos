package Client;

import java.io.IOException;

import Engine.Item;

public class CigaretteClient extends AbastractSmokerClient {
    protected CigaretteClient() throws IOException {
        super(Item.tabaco);
    }


    public static void main(String[] args) {
        

        try {
            AbastractSmokerClient cigaretteSmoker = new CigaretteClient();
            cigaretteSmoker.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // @Override
    // public void run() {

    //     try {

    //         Accion smokerAction = this.cigaretteSmoker.getAction();
    //         outputObjectClient.writeObject(smokerAction);

    //         while (true) { 
                

    //             switch (smokerAction) {
    //                 case buscar:
                        
    //                     outputObjectClient.writeObject(cigaretteSmoker);
                        
    //                     // System.out.println("ta jodiendo");
    //                     // System.out.println("En el cliente:"+inputObjectClient.readObject());
    //                     cigaretteSmoker.addMissingIngredient((Item) inputObjectClient.readObject());
    //                     // System.out.println("clase cliente:" + cigaretteSmoker.getMissingIngredients());
    //                     // System.out.println("ta jodiendo 2");
    //                     // if (!(inputObjectClient.readObject() instanceof Item)) {
    //                     //     System.out.println("alo");
    //                     //     Item recivedItem = (Item) inputObjectClient.readObject();

    //                     //     if (!cigaretteSmoker.getMissingIngredients().contains(recivedItem)) {
    //                     //     System.out.println("alo 2");
                                
    //                     //         cigaretteSmoker.addMissingIngredient(recivedItem);
    //                     //     }

    //                     // }
    //                     break;
    //                 case pedir:
                    
    //                 break;
    //                 case fumar:
                        
    //                 break;
    //                 default:
    //                     break;
    //             }

    //     }
    //     } catch (ClassCastException | IOException | ClassNotFoundException e){

    //     }

        
    // }
    
}
