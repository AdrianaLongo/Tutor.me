import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class Useful {
    String message;
    int success;
// serve a creare un oggetto da passare al metodo toJson (library Gson)
    public Useful (String message, int success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage () {
        return this.message;
    }


    public static Gson fetchJson (String address) throws IOException { //at least I think should receive something as a string

        Gson gson = new Gson();
        URL indirizzo = new URL(address);  //Exception MalformedUrl
        InputStreamReader reader = new InputStreamReader(indirizzo.openStream()); //Exception IOEx
        gson.fromJson(reader, Gson.class);
        return gson;
    }

}


/*class MyDto {
    Map<String, String> headers;
    Map<String, String> args;
    String origin;
    String url;
}
*/