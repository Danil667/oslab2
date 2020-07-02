import java.util.ArrayList;
import java.util.Random;

public class Process {
    Random rand = new Random();
    private ArrayList<Thread> threads;
    private String description = "Процесс  ";
    private int quantTime;
    private int currentTime;
    private int count;
    public Process(int procNumb, int quantTime) {
        this.quantTime = quantTime;
        this.currentTime = quantTime;
        this.description += (procNumb+1);
        threads = new ArrayList<Thread>();
        count = 0;
        for (int i = 0; i < rand.nextInt(5)+1; i++) {
            threads.add(new Thread(i, rand.nextInt(10)+1, 4));
            count++;
        }
    }

    public boolean isEmpty() {
        if (threads.size() == 0)
            return true;
        return false;
    }
    public int getCount() {
        return count;
    }
    public void setCount() {
        count -=1;
    }
    public Thread getThread(int i) {
        if (threads.size() == 0 || i >= threads.size()) return null;
        return threads.get(i);
    }
    public void deleteThread(int i) {
        threads.remove(i);
        count--;
    }

    public String getDescription() {
        return description;
    }
    public String getThreadsDescription() {
        String str = "";
        str += description + " QuantTime = " + quantTime + "\n";
        for(var thread : threads) {
            str += "   " + thread.getDescription() + " QuantTime:" + thread.getQuantTime() + "\n";
        }
        return str;
    }
}
