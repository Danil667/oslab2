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
    public Thread getThread(int i) {
        if (threads.size() == 0) return null;
        return threads.get(i);
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
    public void run(int indTh)
    {
        System.out.println(getThread(indTh).getDescription() + " quant = " + getThread(indTh).getQuantTime());
        if (getThread(indTh).getQuantTime() >= 0)
        {
            getThread(indTh).decreaseTime();
            decreaseCurrentTime();

            if (getThread(indTh).getQuantTime() == 0)
            {
                System.out.println(getThread(indTh).getDescription() + " завершил свою работу");
                deleteThread(indTh);
            }
        }
    }

    public boolean restore(int i)
    {
        if (getCurrentTime() == 0 && getThread(i).getQuantTime() > 0)
        {
            currentTime = quantTime;
            return true;
        }
        return false;
    }
}
