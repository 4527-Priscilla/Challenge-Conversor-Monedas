//Clase que representa el modelo de datos de la respuesta JSON
//de la API de consulta por pares.
// Gson mapeará automáticamente los campos del JSON a las variables de esta clase.

public class ConversionDatos {

    // 1. Variable Privada: El nombre 'conversion_rate' debe coincidir EXACTAMENTE 
    //    con el campo en el JSON de la API para que Gson lo reconozca.
    private double conversion_rate;

    // 2. Constructor vacío para la inicialización por Gson
    public ConversionDatos() {
    }

    // 3. Método Getter: Permite a la clase ConversorApp acceder al valor privado.
    public double getConversionRate() {
        return conversion_rate;
    }
}