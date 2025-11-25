import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Variables
        int opcion = 1;
        String monedaA = "";
        String monedaB = "";
        double valor;
        String apiKey = "de3254132c20d5039b1e39f1";



        Scanner input = new Scanner(System.in);

        while (opcion != 3) {
            try {
                HttpClient client = HttpClient.newHttpClient();


                System.out.println("\nBienvenido al sistema conversor de monedas.");
                System.out.println("\nPor favor ingresa la opción deseada.");
                System.out.println("\n1 - Conocer las monedas soportadas.");
                System.out.println("2 - Realizar una conversión de moneda.");
                System.out.println("3 - Salir.");
                opcion = input.nextInt();
                input.nextLine();

                if (opcion == 3) {
                    System.out.println("Gracias por tu preferencia.");
                    input.close();
                    break;

                } else if (opcion == 2) {
                    System.out.println("\nIngresa los tres caracteres de la moneda a convertir.");
                    monedaA = input.nextLine().trim().toUpperCase();
                    System.out.println("Ingresa los tres caracteres de la moneda a la que quieres convertir los "+ monedaA +".");
                    monedaB = input.nextLine().trim().toUpperCase();
                    System.out.println("¿Cuantos " + monedaA + " quieres convertir a " + monedaB + "?");
                    valor = input.nextDouble();
                    String direccion = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+monedaA+"/"+monedaB+"/"+valor;
                    String direccionEnriquecidaB = "https://v6.exchangerate-api.com/v6/"+apiKey+"/enriched/"+monedaA+"/"+monedaB;
                    String direccionEnriquecidaA = "https://v6.exchangerate-api.com/v6/"+apiKey+"/enriched/"+monedaB+"/"+monedaA;

                    HttpRequest request = HttpRequest.newBuilder() //httprequest de la primera dirección con tipo de cambio
                            .uri(URI.create(direccion))
                            .build();
                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    Gson gson = new Gson();

                    ResultadoConversor datos = gson.fromJson(json, ResultadoConversor.class);

                    HttpRequest request2 = HttpRequest.newBuilder() //httprequest del enriched data MonedaB de la api
                                    .uri(URI.create(direccionEnriquecidaB))
                                    .build();

                    HttpResponse<String> response2 = client
                            .send(request2, HttpResponse.BodyHandlers.ofString());

                    String json2 = response2.body();

                    DatosMoneda datosB = gson.fromJson(json2, DatosMoneda.class);

                    HttpRequest request3 = HttpRequest.newBuilder() //httprequest del enriched data MonedaA de la api
                            .uri(URI.create(direccionEnriquecidaA))
                            .build();

                    HttpResponse<String> response3 = client
                            .send(request3, HttpResponse.BodyHandlers.ofString());

                    String json3 = response3.body();

                    DatosMoneda datosA = gson.fromJson(json3, DatosMoneda.class);

                    System.out.println(direccionEnriquecidaA);
                    System.out.println(direccionEnriquecidaB);
                    System.out.println("\nCon la fecha y hora de actualización: "+ datos.time_last_update_utc);
                    System.out.println("Información de monedas a cambiar:");
                    System.out.println("El tiempo de cambio actual es: " + datos.conversion_rate +" "+monedaB+ " por cada "
                            + monedaA);
                    System.out.println("$" + valor + " " + monedaA + " "
                            + datosA.target_data.currency_name + " del país: "
                            + datosA.target_data.locale);

                    System.out.println("$" + datos.conversion_result + " "
                            + datosB.target_data.currency_name + " del país: "
                            + datosB.target_data.locale);

                } else if (opcion == 1) {
                    List<Map.Entry<String, String>> entradas =
                            new ArrayList<>(CatalogoDeMonedas.Nombre_Moneda.entrySet());
                    entradas.sort(Map.Entry.comparingByKey());
                    int columnas = 4;
                    int anchoColumnas = 35;
                    int contador = 0;
                    System.out.println("\nMonedas soportadas: \n");
                    for (Map.Entry<String, String> entry : entradas) {
                        String codigoMoneda = entry.getKey();
                        String nombre = entry.getValue();
                        System.out.printf("%-5s %-"+(anchoColumnas-5)+"s", codigoMoneda, nombre);
                        contador++;

                        if (contador % columnas == 0) {
                            System.out.println();
                        }
                    }


                }
            } catch (InputMismatchException einput) {
                System.out.println("Error: " + einput.getClass().getSimpleName());
                System.out.println("Debes de escribir un número válido.");
                input.nextLine();

            } catch (NullPointerException npe) {
                System.out.println("Error interno: " + npe.getClass().getSimpleName());
            } catch (Exception eclient) {
                System.out.println("Error al conectarse al servidor: " + eclient.getClass().getSimpleName());

            }
        }
    }
}