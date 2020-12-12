import com.google.gson.Gson;
import dao.Slot;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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

    public static ArrayList<Slot> getSlotLiberi (ArrayList<Slot> slotOccupati) {

        ArrayList<Slot> slotGenerici = new ArrayList<>();
        slotGenerici.add(new Slot("LUN1"));
        slotGenerici.add(new Slot ("LUN2"));
        slotGenerici.add(new Slot ("LUN3"));
        slotGenerici.add(new Slot ("LUN4"));
        slotGenerici.add(new Slot ("MAR1"));
        slotGenerici.add(new Slot ("MAR2"));
        slotGenerici.add(new Slot ("MAR3"));
        slotGenerici.add(new Slot ("MAR4"));
        slotGenerici.add(new Slot ("MER1"));
        slotGenerici.add(new Slot ("MER2"));
        slotGenerici.add(new Slot ("MER3"));
        slotGenerici.add(new Slot ("MER4"));
        slotGenerici.add(new Slot ("GIO1"));
        slotGenerici.add(new Slot ("GIO2"));
        slotGenerici.add(new Slot ("GIO3"));
        slotGenerici.add(new Slot ("GIO4"));
        slotGenerici.add(new Slot ("VEN1"));
        slotGenerici.add(new Slot ("VEN2"));
        slotGenerici.add(new Slot ("VEN3"));
        slotGenerici.add(new Slot ("VEN4"));

        for(Slot slotOcc : slotOccupati) {
            slotGenerici.removeIf(slotLib -> slotOcc.getSezione().equals(slotLib.getSezione()));
        }
        return slotGenerici;
    }

}


/*class MyDto {
    Map<String, String> headers;
    Map<String, String> args;
    String origin;
    String url;
}
*/