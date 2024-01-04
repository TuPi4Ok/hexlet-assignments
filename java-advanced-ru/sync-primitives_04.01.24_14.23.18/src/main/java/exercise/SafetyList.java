package exercise;

public class SafetyList {
    // BEGIN
    private int[] mas = new int[5000];
    private int size;

    public SafetyList() {
        size = 0;
    }

    public synchronized void add(int num) {
        mas[size] = num;
        size++;
    }

    public int get(int index) {
        return mas[index];
    }
    public int getSize() {
        return this.size;
    }
    // END
}
