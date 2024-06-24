package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] mass) {
        Map<String, Integer> map = new HashMap<>();
        int min = 0;
        int max = 0;

        MinThread minThread = new MinThread(mass);
        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");

        MaxThread maxThread = new MaxThread(mass);
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        try {
            maxThread.join();
            LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");

            maxThread.join();
            LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

        min = minThread.getMinimum();
        max = maxThread.getMaximum();
        map.put("min", min);
        map.put("max", max);

        LOGGER.log(Level.INFO, "Result: " + map.toString());
        return map;
    }
    // END
}
