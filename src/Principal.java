import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException{

        ConsultarMoneda consultarMoneda = new ConsultarMoneda();
        Moneda moneda =consultarMoneda.convertirMoneda("USD","ARS");

        //vamos  hacer un menu

        consultarMoneda.mostrarMenu();

        generarArchivo generador = new generarArchivo();
        generador.guardarJson(moneda);
    }
}
