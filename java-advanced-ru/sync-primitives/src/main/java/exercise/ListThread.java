package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {
    public SafetyList safetyList;
    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        super.run();
        var random = new Random();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            safetyList.add(random.nextInt());
        }
    }
}
// END
