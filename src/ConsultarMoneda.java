import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultarMoneda {
    Scanner scanner = new Scanner(System.in);
    //vamos a crear nuestro constructor

    public Moneda convertirMoneda(String monedaBase, String monedaConvertir){
        //https://v6.exchangerate-api.com/v6/TU_API_KEY/pair/USD/ARS
        String baseUrl="https://v6.exchangerate-api.com/v6/";
        String apiKey ="21e9a0901ca8da9342be8d05";
        URI direccion = URI
                .create( baseUrl+apiKey+"/pair/" + monedaBase+"/"+monedaConvertir);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();


        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception  e) {
            throw new RuntimeException("no encontre el tipo de cambio");
        }
    }

    public void mostrarMenu() {
        int opcion;

        System.out.println("************************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("************************************************************");
        System.out.println();
        System.out.println("1) Dólar        => Peso argentino");
        System.out.println("2) Peso argent. => Dólar");
        System.out.println("3) Dólar        => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar        => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Salir");
        System.out.println();
        System.out.println("Elija una opción válida:");

        opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        switch (opcion) {
            case 1 -> convertir("USD", "ARS");
            case 2 -> convertir("ARS", "USD");
            case 3 -> convertir("USD", "BRL");
            case 4 -> convertir("BRL", "USD");
            case 5 -> convertir("USD", "COP");
            case 6 -> convertir("COP", "USD");
            case 7 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción inválida.");
        }
    }


    private void convertir(String base, String destino) {
        System.out.print("Ingrese el monto a convertir: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        try {
            Moneda datos = convertirMoneda(base, destino);

            double tasa = datos.conversion_rate();
            double resultado = monto * tasa;

            System.out.println("\n==============================");
            System.out.println("Conversión completada");
            System.out.println(base + " => " + destino);
            System.out.println("Monto ingresado: " + monto);
            System.out.println("Tasa actual: " + tasa);
            System.out.println("Resultado final: " + resultado);
            System.out.println("==============================\n");

        } catch (Exception e) {
            System.out.println("Error al convertir la moneda.");
        }
        mostrarMenu();
    }
}