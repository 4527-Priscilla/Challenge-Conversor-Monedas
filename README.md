**<h1 align="center"> DESAFÍO CONVERSOR DE MONEDAS </h1>**
<h2 align="center">:dollar: EN PROCESO :dollar:</h2>
<h3 align="center">Alura Latam ONE. Practicando con Java: Challenge Conversor de Monedas.</h3>

<p align="center">
    <img src="Monedas_Apiladas.jpg" alt="Imagen monedas apiladas" width="700">
</p>

<p align="center">---------------------------------------------------------------------------------------------------------------------------------------------------</p>

**💰 Conversor de Moneda (Oracle ONE y Alura Latam)**

Este proyecto es una aplicación de consola desarrollada en Java que permite a los usuarios realizar conversiones de moneda en tiempo real, obteniendo las tasas de cambio actualizadas a través de una API externa.

Este desarrollo cumple con el desafío propuesto en la formación **Oracle ONE de Alura Latam.**

🚀 Funcionalidades

El programa ofrece un menú interactivo en la consola para realizar las siguientes conversiones:

- USD (Dólar estadounidense) → ARS (Peso argentino)

- ARS (Peso argentino) → USD (Dólar estadounidense)

- USD (Dólar estadounidense) → BRL (Real brasileño)

- BRL (Real brasileño) → USD (Dólar estadounidense)

- USD (Dólar estadounidense) → COP (Peso colombiano)

- COP (Peso colombiano) → USD (Dólar estadounidense)

Características Técnicas Destacadas

Lógica de Conversión: El cálculo se realiza mediante la fórmula Cantidad * Tasa_Obtenida.

Manejo de Errores: Incluye robustos bloques try-catch para gestionar excepciones de red (IOException), interrupciones (InterruptedException) y errores de formato de entrada (InputMismatchException o JsonSyntaxException).

Menú Iterativo: La aplicación utiliza un bucle do-while para mantener el menú activo hasta que el usuario decida salir (opción 7).

🛠️ Tecnologías y Librerías

Lenguaje: Java 17+

Conectividad: java.net.http.HttpClient (Clases HTTP nativas de Java).

Manejo de JSON: Gson 2.10.1 (Librería de Google para serialización/deserialización de objetos Java a JSON).

API Externa: ExchangeRate-API (Endpoint /pair/ para obtener tasas de conversión directa).

📂 Estructura del Proyecto

El proyecto se separa en tres clases principales:

- ConversorApp.java (Interfaz y control): Contiene el método main(), gestiona el menú, lee la entrada del usuario (Scanner) y presenta el resultado final.

- ConsultaTasa.java (Servicio y conectividad): Se encarga de construir la URL con la API Key, realizar la solicitud HTTP y gestionar la conversión del JSON a un objeto Java mediante Gson.

- ConversionData.java (Modelo de datos): Clase simple para mapear el campo clave conversion_rate de la respuesta JSON.
