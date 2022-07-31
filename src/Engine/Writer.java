package Engine;

public class Writer {
    public static void Write(String message){
        System.out.println(message);
    }

    public static void WriteStandContent(Item item){
        String itemString = item.toString();
        System.out.println("Se lleno el Stand con " + itemString);
    }
}
