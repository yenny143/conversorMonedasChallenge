import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class generarArchivo {

    public void guardarJson (Moneda moneda) throws IOException {
        Gson gson= new GsonBuilder().setPrettyPrinting().create();

        FileWriter fileWriter = new FileWriter( moneda.base_code()+"-"+moneda.target_code()+".json");

        fileWriter.write(gson.toJson(moneda));
        fileWriter.close();
    }
}
