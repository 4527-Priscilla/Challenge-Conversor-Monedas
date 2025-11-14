
 // Clase encargada de toda la lógica de conexión a la API
 //y el manejo del JSON con Gson.


// Importaciones necesarias para la conexión y el manejo de JSON
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConsultaTasa {

    // Usamos static final ya que estas variables no cambian y son compartidas
    private static final String API_KEY = "TU_CLAVE_AQUI"; // Se debe reemplazar por la API KEY obtenida de ExchangeRate-API
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();


    // Función principal de la búsqueda

    // Realiza la solicitud a la API para obtener la tasa de conversión entre dos monedas.
    //return un objeto ConversionData con la tasa, o null si hay un error.

    public static ConversionDatos buscaTasa(String origen, String destino) {
        // Concatenamos la base con las monedas para crear el URL completo
        String urlCompleta = API_BASE_URL + origen + "/" + destino;

        // Aquí necesitamos el bloque try-catch
        try {
            // 1. Creación de la dirección
            URI direccion = URI.create(urlCompleta);

            // 2. Creación de la Solicitud HTTP (HttpRequest)
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // 3. Envío de la Solicitud (puede lanzar IOException)
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Verificación de éxito (Código HTTP 200)
            if (response.statusCode() == 200) {
                // Si el código es 200, usamos Gson para mapear el JSON al objeto ConversionData
                // Aquí el modelo mapea el campo "conversion_rate"
                return gson.fromJson(response.body(), ConversionDatos.class);
            } else {
                // Manejar errores de la API (ej: clave inválida)
                System.err.println("[ERROR API] Error al obtener datos. Código de estado: " + response.statusCode());
                System.err.println("Mensaje de la API: " + response.body());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            // Problemas de red o el hilo fue interrumpido
            System.err.println("[ERROR CONEXIÓN] Problema de red o interrupción: " + e.getMessage());
            return null;
        } catch (JsonSyntaxException e) {
            // El JSON recibido no es válido o la estructura es incorrecta
            System.err.println("[ERROR JSON] No se pudo procesar la respuesta JSON: " + e.getMessage());
            return null;
        }
    }
}