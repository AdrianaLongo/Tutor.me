package utils;

import dao.Slot;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Useful {
    String message;
    int success;
    Object object = null;

    // serve a creare un oggetto da passare al metodo toJson (library Gson)
    public Useful(String message, int success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public Useful() {
    }

    public String getMessage() {
        return this.message;
    }

    public int getSuccess() {
        return this.success;
    }

    public Object getObject() {
        return this.object;
    }

    public static ArrayList<Slot> getSlotLiberi(ArrayList<Slot> slotOccupati) {

        ArrayList<Slot> slotGenerici = new ArrayList<>();
        slotGenerici.add(new Slot("LUN1"));
        slotGenerici.add(new Slot("LUN2"));
        slotGenerici.add(new Slot("LUN3"));
        slotGenerici.add(new Slot("LUN4"));
        slotGenerici.add(new Slot("MAR1"));
        slotGenerici.add(new Slot("MAR2"));
        slotGenerici.add(new Slot("MAR3"));
        slotGenerici.add(new Slot("MAR4"));
        slotGenerici.add(new Slot("MER1"));
        slotGenerici.add(new Slot("MER2"));
        slotGenerici.add(new Slot("MER3"));
        slotGenerici.add(new Slot("MER4"));
        slotGenerici.add(new Slot("GIO1"));
        slotGenerici.add(new Slot("GIO2"));
        slotGenerici.add(new Slot("GIO3"));
        slotGenerici.add(new Slot("GIO4"));
        slotGenerici.add(new Slot("VEN1"));
        slotGenerici.add(new Slot("VEN2"));
        slotGenerici.add(new Slot("VEN3"));
        slotGenerici.add(new Slot("VEN4"));

        for (Slot slotOcc : slotOccupati) {
            slotGenerici.removeIf(slotLib -> slotOcc.getSezione().equals(slotLib.getSezione()));
        }
        return slotGenerici;
    }

    public static int generateId() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(10, 1000000000);
    }

}