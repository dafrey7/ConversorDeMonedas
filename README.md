# Conversor de Monedas – Oracle Next Education

Aplicación de consola en **Java** que consume la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para realizar conversiones de moneda en tiempo real.  
Proyecto desarrollado como parte del desafío del programa **Oracle Next Education (ONE)**.

---

## ✨ Funcionalidades

- Menú interactivo en consola:
  - `1` – Mostrar listado de monedas soportadas (códigos ISO y nombre).
  - `2` – Realizar una conversión de moneda.
  - `3` – Salir del programa.
- Conversión de montos entre dos monedas usando el endpoint `/pair`.
- Uso de la API para obtener:
  - **Tasa de conversión actual (`conversion_rate`)**
  - **Resultado total de la conversión (`conversion_result`)**
- Uso de **enriched data** (si el plan de la API lo permite) para mostrar:
  - Nombre completo de la moneda de origen y destino.
  - País asociado a cada moneda.
- Manejo básico de errores:
  - Entradas no numéricas.
  - Errores de conexión / excepciones generales.
  - Errores internos (por ejemplo, acceso a datos nulos).

---

## 🛠 Tecnologías utilizadas

- **Java** (versión 11+ recomendada)
- `java.net.http.HttpClient` para las peticiones HTTP.
- **Gson** para deserializar las respuestas JSON.
- API: [ExchangeRate-API](https://www.exchangerate-api.com/)

---

## 📁 Estructura del proyecto

Archivos principales:

- `Main.java`  
  Contiene el menú de consola, lectura de datos del usuario y las llamadas a la API.

- `ResultadoConversor.java`  
  Modelo para mapear la respuesta del endpoint `/pair` (tasa y resultado de conversión).

- `DatosMoneda.java`  
  Modelo para mapear la respuesta del endpoint `/enriched` (información adicional de la moneda).

- `TargetData.java`  
  Clase anidada utilizada dentro de `DatosMoneda` para representar el objeto `target_data` del JSON (nombre de la moneda, país, símbolo, etc.).

- `CatalogoDeMonedas.java`  
  Mapa con códigos de moneda (ISO 4217) y sus nombres en inglés. Se usa para mostrar el listado de monedas soportadas en la opción 1 del menú.

---

## ⚙️ Requisitos previos

- Java 11 o superior instalado.
- Dependencia de **Gson** añadida al proyecto.
