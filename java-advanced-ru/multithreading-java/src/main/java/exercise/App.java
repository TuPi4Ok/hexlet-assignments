package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] mas) {
        MaxThread maxThread = new MaxThread(mas);
        MinThread minThread = new MinThread(mas);
        minThread.start();
        maxThread.start();
        LOGGER.log(Level.INFO, minThread.getName() + " started");
        LOGGER.log(Level.INFO, maxThread.getName() + " started");

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

        LOGGER.log(Level.INFO, minThread.getName() + " finished");
        LOGGER.log(Level.INFO, maxThread.getName() + " finished");
        Map<String, Integer> result = new HashMap<>();
        result.put("min", minThread.getMin());
        result.put("max", maxThread.getMax());
        return result;
    }
    // END
}
