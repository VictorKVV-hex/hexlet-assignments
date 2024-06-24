package exercise;

import java.util.Arrays;

// BEGIN
class MaxThread extends Thread {
    private int[] mass;
    private int maximum;

    MaxThread(int[] mass) {
        this.mass = mass;
    }

    @Override
    public void run () {
//        Arrays.stream(mass).max().getAsInt();
        int max = mass[0];
        for (int curmass : mass) {
            if (curmass > max) {
                max = curmass;
            }
        }
        maximum = max;
    }

    public int getMaximum() {
        return maximum;
    }
}
// END
