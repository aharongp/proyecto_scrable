import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Diccionario {

    // URL base de la API de Oxford Dictionaries para el idioma español
    private static final String API_URL = "https://od-api-sandbox.oxforddictionaries.com/api/v2/entries/es/";

    // Tu App ID y App Key obtenidos del portal de Oxford Dictionaries
    private static final String APP_ID = "8ac4989f";  // Reemplaza con tu App ID
    private static final String APP_KEY = "238e6ce7812ed3db300a06ae597a1344";  // Reemplaza con tu App Key

    // Método para verificar si una palabra existe en el diccionario
    public boolean verificarPalabra(String palabra) {
        try {
            // Construir la URL para hacer la solicitud
            String urlString = API_URL + palabra.toLowerCase();  // Convertimos la palabra a minúsculas
            URL url = new URL(urlString);

            // Crear una conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Establecer los encabezados de autenticación
            conn.setRequestProperty("app_id", APP_ID);  // Añadir App ID
            conn.setRequestProperty("app_key", APP_KEY);  // Añadir App Key

            // Obtener el código de respuesta de la API
            int responseCode = conn.getResponseCode();

            // Si la respuesta es 200 OK, la palabra existe
            if (responseCode == 200) {
                return true;  // La palabra existe en el diccionario
            } else if (responseCode == 404) {
                return false;  // La palabra no existe
            } else {
                // En caso de error, mostramos el código de respuesta y el mensaje de error
                System.out.println("Error en la API: Código de respuesta " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Respuesta de error: " + response.toString());
            }

        } catch (Exception e) {
            System.out.println("Error al verificar la palabra: " + e.getMessage());
        }
        return false;  // Si ocurre un error, asumimos que la palabra no existe
    }

    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();

        // Solicitar la palabra que deseas verificar
        String palabra = "casa";  // Cambia esta palabra por la que quieras verificar

        boolean existe = diccionario.verificarPalabra(palabra);

        // Mostrar el resultado
        if (existe) {
            System.out.println("La palabra '" + palabra + "' existe en el diccionario.");
        } else {
            System.out.println("La palabra '" + palabra + "' no existe en el diccionario.");
        }
    }
}
