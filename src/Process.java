import java.util.ArrayList;
import java.util.Random;

public class Process {
    Random rand = new Random();
    private ArrayList<Thread> threads;
    private String description = "Процесс  ";
    private int quantTime;
    private int currentTime;

    public Process(int procNumb, int quantTime) {
        this.quantTime = quantTime;
        this.currentTime = quantTime;
        this.description += (procNumb+1);

        threads = new ArrayList<Thread>();
        for (int i = 0; i < rand.nextInt(5)+1; i++) {
            threads.add(new Thread(i, rand.nextInt(10)+1));
        }
    }

    public boolean isEmpty() {
        if (threads.size() == 0)
            return true;
        return false;
    }

    public Thread getThread() {
        if (threads.size() == 0) return null;
        return threads.get(0);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getQuantTime() {
        return quantTime;
    }

    public void decreaseCurrentTime() {
        --currentTime;
    }

    public void deleteThread() {
        threads.remove(0);
    }

    public String getDescription() {
        return description;
    }

    public void restoreCurrentQuantTime() {
        currentTime = quantTime;
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
