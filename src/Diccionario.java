import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Diccionario {
    private HashSet<String> palabras;

    public Diccionario(String rutaArchivo) throws IOException {
        palabras = new HashSet<>();
        cargarDiccionario(rutaArchivo);
    }

    private void cargarDiccionario(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea.trim().toLowerCase());
            }
        }
    }

    public boolean existePalabra(String palabra) {
        return palabras.contains(palabra.toLowerCase());
    }

    public static void main(String[] args) {
        try {
            // Carga el diccionario desde el archivo
            Diccionario diccionario = new Diccionario("C:/proyePOO/src/listado-general.txt");

            // Ejemplo de uso
            String palabra = "abajo";
            if (diccionario.existePalabra(palabra)) {
                System.out.println("La palabra '" + palabra + "' existe en el diccionario.");
            } else {
                System.out.println("La palabra '" + palabra + "' NO existe en el diccionario.");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
}
