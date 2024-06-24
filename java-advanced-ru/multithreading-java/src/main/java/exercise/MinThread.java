package exercise;

import java.util.Arrays;

// BEGIN
class MinThread extends Thread {
    private int[] mass;
    private int minimum;

    public MinThread(int[] mass) {
        this.mass = mass;
    }
    @Override
    public void run() {
//        Arrays.stream(mass).min().getAsInt();
        int min = mass[0];
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] < min) {
                min = mass[i];
            }
        }
        minimum = min;
    }

    public int getMinimum() {
        return minimum;
    }
}
// END
