import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Diccionario {

    private static final String API_URL = "https://od-api-sandbox.oxforddictionaries.com/api/v2/entries/es/";

    private static final String APP_ID = "8ac4989f";
    private static final String APP_KEY = "238e6ce7812ed3db300a06ae597a1344";

    // Método para verificar si una palabra existe en el diccionario
    public boolean verificarPalabra(String palabra) {
        try {
            String urlString = API_URL + palabra.toLowerCase();
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("app_id", APP_ID);
            conn.setRequestProperty("app_key", APP_KEY);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                return true;
            } else if (responseCode == 404) {
                return false;
            } else {
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
        return false;
    }
}
