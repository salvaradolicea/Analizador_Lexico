import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {

            // Leer programa fuente
            List<String> programa = FileManager.leerArchivo("progfte.txt");

            Lexer lexer = new Lexer();
            List<Token> tokens = lexer.analizar(programa);

            // ===== progfte.tok =====
            List<String> salidaTokens = new ArrayList<>();
            for (Token t : tokens) {
                salidaTokens.add(t.toString() + " línea " + t.getLinea());
                System.out.println(t);
            }
            FileManager.escribirArchivo("progfte.tok", salidaTokens);

            // ===== progfte.tab =====
            List<String> salidaTabla = new ArrayList<>();
            for (String id : lexer.getTablaSimbolos().getTabla()) {
                salidaTabla.add(id);
            }
            FileManager.escribirArchivo("progfte.tab", salidaTabla);

            // ===== progfte.dep =====
            List<String> depurado = new ArrayList<>();
            for (String linea : programa) {
                if (!linea.contains("/*")) {
                    depurado.add(linea.replaceAll("\\s+", ""));
                }
            }
            FileManager.escribirArchivo("progfte.dep", depurado);

            System.out.println("✅ Análisis léxico completado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}