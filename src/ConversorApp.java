
//Clase principal que maneja el menú y la interacción con el usuario.
//Usa la clase ConsultaTasa para obtener los datos de la API.

import java.sql.SQLOutput;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        // Inicialización de la herramienta para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Mensaje de bienvenida al iniciar el programa
        System.out.println("**************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("\n");

        // Bucle principal: se ejecuta al menos una vez y se repite hasta que opcion sea 7
        do {
            mostrarMenu();

            try {
                //  Verifica si la entrada es un número entero
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    if (opcion >= 1 && opcion <= 8) {
                        ejecutarOpcion(opcion, scanner);
                    } else if (opcion == 9) {
                        System.out.println("\nGracias por usar el conversor. ¡Hasta pronto!");
                    } else {
                        System.out.println("\n[ERROR] Opción no válida. Por favor, elija un número del 1 al 9.");
                    }
                } else {
                    // Si no es un entero, muestra error y limpia la memoria del scanner
                    System.out.println("\n[ERROR] Entrada inválida. Por favor, ingrese un número.");
                    scanner.nextLine();
                    opcion = 0; // Para asegurar que el bucle continúe
                }
            } catch (Exception e) {
                System.err.println("\n[ERROR FATAL] Ocurrió un error inesperado. Saliendo...");
                opcion = 9;
            }
        } while (opcion != 9);

        // Cierra el scanner al finalizar el programa
        scanner.close();
    }

    // ----------------------------------------------------
    // MÉTODOS DE APOYO
    // ----------------------------------------------------

    private static void mostrarMenu() {
        System.out.println("1) Dólar =>> Peso argentino (USD a ARS)");
        System.out.println("2) Peso argentino =>> Dólar (ARS a USD)");
        System.out.println("3) Dólar =>> Real brasileño (USD a BRL)");
        System.out.println("4) Real brasileño =>> Dólar (BRL a USD)");
        System.out.println("5) Dólar =>> Peso colombiano (USD a COP)");
        System.out.println("6) Peso colombiano =>> Dólar (COP a USD)");
        System.out.println("7) Dólar =>> Peso chileno (USD a CLP)");
        System.out.println("8) Peso chileno =>> Dólar (CLP a USD)");
        System.out.println("9) Salir");
        System.out.print("Elija una opción válida (1-9): ");
        System.out.println("\n**************************************************");
    }

    private static void ejecutarOpcion(int opcion, Scanner scanner) {
        String monedaOrigen = "";
        String monedaDestino = "";

        // Asignación de códigos de moneda basada en la opción
        switch (opcion) {
            case 1: monedaOrigen = "USD"; monedaDestino = "ARS"; break;
            case 2: monedaOrigen = "ARS"; monedaDestino = "USD"; break;
            case 3: monedaOrigen = "USD"; monedaDestino = "BRL"; break;
            case 4: monedaOrigen = "BRL"; monedaDestino = "USD"; break;
            case 5: monedaOrigen = "USD"; monedaDestino = "COP"; break;
            case 6: monedaOrigen = "COP"; monedaDestino = "USD"; break;
            case 7: monedaOrigen = "USD"; monedaDestino = "CLP"; break;
            case 8: monedaOrigen = "CLP"; monedaDestino = "USD"; break;
        }

        System.out.printf("\nHa elegido convertir %s >> %s\n", monedaOrigen, monedaDestino);

        System.out.print("Ingrese el valor que desea convertir: ");

        try {
            // Se usa nextDouble para leer el valor numérico (puede ser decimal)
            double cantidad = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea pendiente



            // Aquí se conecta con la clase ConsultaTasa

            // Llama a la clase de servicio (ConsultaTasa)
            ConversionDatos datosConversion = ConsultaTasa.buscaTasa(monedaOrigen, monedaDestino);

            // Verifica si la consulta fue exitosa
            if (datosConversion != null) {
                // Obtiene la tasa de conversión usando el metodo Getter del objeto de datos
                double tasaConversion = datosConversion.getConversionRate();
                double resultado = cantidad * tasaConversion;

                // Muestra el resultado final
                System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]\n",
                        cantidad, monedaOrigen, resultado, monedaDestino);
                System.out.println("\n");
            } else {
                // Mensaje si la función buscaTasa devolvió null (falló la conexión/API)
                System.out.println("[ERROR] No se pudo realizar la conversión. Verifique la conexión o clave de API.");
            }

        } catch (java.util.InputMismatchException e) {
            // Manejar error si el usuario ingresa letras en lugar de un número para la cantidad
            System.out.println("\n[ERROR] Por favor, ingrese una cantidad numérica válida.");
            scanner.nextLine(); // Limpiar
        }
    }
}