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
        while (processes.size() != 0) {
            int iTh = 0;
            for (int i = 0; i < processes.size(); i++) {
                int a = processes.get(i).getCount();
                System.out.println("Выполняется " + processes.get(i).getDescription() + " QuantTime = " + processes.get(i).getQuantTime());
                while (processes.get(i).getCurrentTime() > 0) {
                    processes.get(i).run(0);
                    if (processes.get(i).isEmpty()) {
                        System.out.println(processes.get(i).getDescription() + " завершил свою работу");
                        processes.remove(i);
                        i--;
                        break;
                    }
                    if (processes.get(i).restore(0)) {
                        break;
                    }
                }
                System.out.println();
            }
        }
    }
}
