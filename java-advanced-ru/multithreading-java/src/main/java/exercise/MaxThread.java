package exercise;

// BEGIN
public class MaxThread extends Thread {
    int[] m;
    int max;

    public MaxThread(int[] m) {
        this.m = m;
    }

    @Override
    public void run() {
        super.run();
        max = Integer.MIN_VALUE;
        for(int i=0; i < m.length; i++) {
            if(m[i] > max)
                max = m[i];
        }
    }

    public int getMax() {
        return this.max;
    }
}
// END
