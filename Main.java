import java.util.*;

public class Main {

    public static void main(String[] args) {

        try{

            List<String> programa = FileManager.leerArchivo("progfte.txt");

            Lexer lexer = new Lexer();

            List<Token> tokens = lexer.analizar(programa);

            for(Token t : tokens){
                System.out.println(t);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}