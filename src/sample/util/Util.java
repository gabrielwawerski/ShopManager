package sample.util;

import java.util.concurrent.ThreadLocalRandom;

public final class Util {
    /**
     * Returns a random number between min and max (both inclusive)
     */
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
