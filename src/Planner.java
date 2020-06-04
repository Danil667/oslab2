import java.util.ArrayList;
import java.util.Random;

public class Planner {
    private static ArrayList<Process> processes;
    private static Random rand = new Random();
    public Planner() {
        processes = new ArrayList<Process>();
        Random random = new Random();
        int countProcesses = random.nextInt(8)+2;

        for (int i = 0; i < countProcesses; i++) {
            processes.add(new Process(i, rand.nextInt(10)+2));
        }
        getInfo();
    }

    private static void getInfo() {
        for(var proc : processes) {
            System.out.println(proc.getThreadsDescription());
        }
    }

    public void start() {
        int count = 0;
        int indTh = 0;
        while (processes.size() != 0) {
            for (int i = 0; i < processes.size(); i++) {
                System.out.println("Выполняется " + processes.get(i).getDescription() + " QuantTime = " + processes.get(i).getQuantTime());
                int quantTh = processes.get(i).getThread(indTh).quant();
                while (processes.get(i).getCurrentTime() > 0) {
                    System.out.println(processes.get(i).getThread(indTh).getDescription() + " quant = " + processes.get(i).getThread(indTh).getQuantTime());
                    if (processes.get(i).getThread(indTh).getQuantTime() >= 0) {
                        processes.get(i).getThread(indTh).decreaseTime();
                        processes.get(i).decreaseCurrentTime();
                        if (processes.get(i).getThread(indTh).getQuantTime() == 0) {
                            System.out.println(processes.get(i).getThread(indTh).getDescription() + " завершил свою работу");
                            processes.get(i).deleteThread(indTh);
                            processes.get(i).setCount();
                            count =0;
                        }
                    }
                    if (processes.get(i).isEmpty()) {
                        System.out.println(processes.get(i).getDescription() + " завершил свою работу");
                        processes.remove(i);
                        i--;
                        count = 0;
                        break;
                    }
                    if (count == quantTh){
                        count = 0;
                        indTh++;
                    }
                    if (indTh >= processes.get(i).getCount()) {
                        indTh = 0;
                        count= 0;
                    }
                    if ((processes.get(i).getCurrentTime() == 0) && processes.get(i).getThread(indTh).getQuantTime() > 0) {
                        processes.get(i).restoreCurrentQuantTime();
                        count=0;
                        indTh = 0;
                        break;
                    }
                    count++;
                }
                System.out.println();
            }
        }
    }
}
