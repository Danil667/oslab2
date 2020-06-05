import java.util.ArrayList;
import java.util.Random;

public class Process {
    Random rand = new Random();
    private ArrayList<Thread> threads;
    private String description = "Процесс  ";
    private int count;
    public Process(int procNumb) {
        this.description += (procNumb+1);
        threads = new ArrayList<Thread>();
        count = 0;
        for (int i = 0; i < rand.nextInt(5)+1; i++) {
            threads.add(new Thread(i, rand.nextInt(10)+1, 2));
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
        count--;
    }
    public Thread getThread(int i) {
        if (threads.size() == 0) return null;
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
        str += description + "\n";
        for(var thread : threads) {
            str += "   " + thread.getDescription() + " QuantTime:" + thread.getQuantTime() + "\n";
        }
        return str;
    }
}
