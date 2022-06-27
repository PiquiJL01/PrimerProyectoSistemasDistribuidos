package Engine;

public class Writer {
    public static void Write(String message){
        System.out.println(message);
    }

    public static void WriteStandContent(Item item){
        String itemString = "";
        switch (item){
            case papel:
                itemString = "Papel";
            case fosforo:
                itemString = "Fosforo";
            case tabaco:
                itemString = "Tabaco";
        }
        System.out.println("Se lleno el Stand con " + itemString);
    }
}
