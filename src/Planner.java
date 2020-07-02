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
                while(processes.get(i).getThread(indTh) == null){
                    i++;
                    if (i == processes.size()) {
                        indTh = 0;
                        i=0;
                        break;
                    }
                }
                System.out.println("Выполняется " + processes.get(i).getThread(indTh).getDescription());
                count = processes.get(i).getThread(indTh).quant();
                while (count > 0 && i < processes.size()) {
                    while(processes.get(i).getThread(indTh) == null){
                        i++;
                        if (i >= processes.size()) {
                            i=0;
                            break;
                        }
                    }
                    while(processes.get(i).getThread(indTh) == null){
                        i++;
                        if (i >= processes.size()) {
                            i=0;
                            break;
                        }
                    }
                    System.out.println(processes.get(i).getDescription() + processes.get(i).getThread(indTh).getDescription() + " quant = " + processes.get(i).getThread(indTh).getQuantTime());
                    if (processes.get(i).getThread(indTh).getQuantTime() >= 0) {
                        processes.get(i).getThread(indTh).decreaseTime();
                        if (processes.get(i).getThread(indTh).getQuantTime() == 0) {
                            System.out.println(processes.get(i).getDescription() + processes.get(i).getThread(indTh).getDescription() + " завершил свою работу");
                            processes.get(i).deleteThread(indTh);
                            processes.get(i).setCount();
                            count =0;
                        }
                    }
                    if (processes.get(i).isEmpty()) {
                        System.out.println(processes.get(i).getDescription() + " завершил свою работу");
                        processes.remove(i);
                        i++;
                    }

                    count--;
                    i++;
                    if(count > 0 && i == processes.size())
                        i = 0;
                }
                indTh++;
                i = -1;
                System.out.println();
            }
        }
    }
}
