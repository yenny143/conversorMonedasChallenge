import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        ConsultarMoneda consultarMoneda = new ConsultarMoneda();
        Moneda moneda =consultarMoneda.convertirMoneda("USD","ARS");

        //vamos  hacer un menu

        consultarMoneda.mostrarMenu();

        generarArchivo generador = new generarArchivo();
        generador.guardarJson(moneda);
    }
}
