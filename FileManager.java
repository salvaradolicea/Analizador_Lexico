//esta clase maneja la lectura y escritura de archivos
import java.io.*;
import java.util.*;

public class FileManager {
    //devuelve una lista con todas las líneas del archivo
    public static List<String> leerArchivo(String nombre) throws IOException {
        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nombre));

        String linea;
        while ((linea = br.readLine()) != null) {
            lineas.add(linea);
        }

        br.close();
        return lineas;
    }
    //escribe una lista de cadenas en un archivo
    public static void escribirArchivo(String nombre, List<String> contenido) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombre));

        for (String linea : contenido) {
            bw.write(linea);
            bw.newLine();
        }

        bw.close();
    }
}
//esto permite cargar el programa fuente progfte.txt
//y guardar los resultados del análisis .tok, .tab, .dep