package exercise;

// BEGIN
public class MinThread extends Thread {
    int[] m;
    int min;

    public MinThread(int[] m) {
        this.m = m;
    }

    @Override
    public void run() {
        super.run();
        min = Integer.MAX_VALUE;
        for(int i=0; i < m.length; i++) {
            if(m[i] < min)
                min = m[i];
        }
    }

    public int getMin() {
        return this.min;
    }
}
// END
