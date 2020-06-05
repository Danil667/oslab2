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
            processes.add(new Process(i));
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
            boolean flag = false;
            for (int i = 0; i < processes.size(); i++) {
                if (indTh >= processes.get(i).getCount()){
                    while(indTh > processes.get(i).getCount()){
                        i++;
                        if(i >= processes.size()){
                            indTh = 0;
                            i = 0;
                            break;
                        }
                    }
                }
                System.out.println("Выполняется " + processes.get(i).getDescription());
                while (processes.get(i).getThread(indTh).getCurrentTime() > 0) {
                    if (indTh > processes.get(i).getCount()){
                        break;
                    }
                    int quantTh = processes.get(i).getThread(indTh).quant();
                    System.out.println(processes.get(i).getThread(indTh).getDescription() + " quant = " + processes.get(i).getThread(indTh).getQuantTime());
                    if (processes.get(i).getThread(indTh).getQuantTime() >= 0) {
                        processes.get(i).getThread(indTh).decreaseTime();
                        if (processes.get(i).getThread(indTh).getQuantTime() == 0) {
                            System.out.println(processes.get(i).getThread(indTh).getDescription() + " завершил свою работу");
                            processes.get(i).setCount();
                            processes.get(i).deleteThread(indTh);
                            count = 0;
                            if (processes.get(i).isEmpty()) {
                                System.out.println(processes.get(i).getDescription() + " завершил свою работу");

                                processes.get(i).setCount();
                                processes.remove(i);
                                i--;
                                count = 0;
                                break;
                            }
                            break;
                        }
                    }
                    if (processes.get(i).isEmpty()) {
                        System.out.println(processes.get(i).getDescription() + " завершил свою работу");

                        processes.get(i).setCount();
                        processes.remove(i);
                        i--;
                        count = 0;
                        break;
                    }
                    if (count == quantTh || indTh >= processes.get(i).getCount()) {
                        count = 0;
                        break;
                    }
                    if ((processes.get(i).getThread(indTh).getCurrentTime() == 0) && processes.get(i).getThread(indTh).getQuantTime() > 0) {
                        processes.get(i).getThread(indTh).restoreCurrentQuantTime();
                        count=0;
                        indTh = 0;
                        break;
                    }
                    count++;
                }
                System.out.println();
            }

            indTh++;
        }
    }
}
