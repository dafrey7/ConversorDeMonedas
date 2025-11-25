# Conversor de Monedas – Oracle Next Education

Una aplicación de consola en **Java** que utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para realizar conversiones de moneda en tiempo real. Este proyecto fue desarrollado como parte del desafío del programa **Oracle Next Education (ONE)**.

---

## ✨ Funcionalidades Principales

*   **Menú Interactivo en Consola:**
    *   `1` – Mostrar listado de monedas soportadas (códigos ISO y nombre).
    *   `2` – Realizar una conversión de moneda.
    *   `3` – Salir del programa.
*   **Conversión de Montos:** Permite la conversión entre dos monedas utilizando el endpoint `/pair`.
*   **Información de la API:**
    *   Obtención de la **tasa de conversión actual (`conversion_rate`)**.
    *   Cálculo del **resultado total de la conversión (`conversion_result`)**.
*   **Datos Enriquecidos (Enriched Data):** (Si el plan de la API lo permite)
    *   Muestra el nombre completo de la moneda de origen y destino.
    *   Indica el país asociado a cada moneda.
*   **Manejo de Errores:**
    *   Control de entradas no numéricas.
    *   Gestión de errores de conexión y excepciones generales.
    *   Manejo de errores internos (por ejemplo, acceso a datos nulos).

---

## 🛠 Tecnologías Utilizadas

*   **Java** (versión 11+ recomendada)
*   `java.net.http.HttpClient` para realizar peticiones HTTP.
*   **Gson** para deserializar las respuestas JSON.
*   API: [ExchangeRate-API](https://www.exchangerate-api.com/)

---

## 📁 Estructura del Proyecto

*   `Main.java`:
    *   Contiene el menú de consola.
    *   Gestiona la lectura de datos del usuario.
    *   Realiza las llamadas a la API.
*   `ResultadoConversor.java`:
    *   Modelo para mapear la respuesta del endpoint `/pair`.
    *   Incluye la tasa y el resultado de la conversión.
*   `DatosMoneda.java`:
    *   Modelo para mapear la respuesta del endpoint `/enriched`.
    *   Contiene información adicional de la moneda.
*   `TargetData.java`:
    *   Clase anidada dentro de `DatosMoneda`.
    *   Representa el objeto `target_data` del JSON (nombre de la moneda, país, símbolo, etc.).
*   `CatalogoDeMonedas.java`:
    *   Mapa con códigos de moneda (ISO 4217) y sus nombres en inglés.
    *   Se utiliza para mostrar el listado de monedas soportadas en la opción 1 del menú.

---

## ⚙️ Requisitos Previos

*   Java 11 o superior instalado.
*   Dependencia de **Gson** añadida al proyecto.
