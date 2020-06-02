public class Thread {
    private String description = "Поток ";
    private int quantTime;

    public Thread(int threadNumb, int quantTime) {
        this.description += (threadNumb+1);
        this.quantTime = quantTime;
    }

    public int getQuantTime() {
        return quantTime;
    }

    public String getDescription() {
        return description;
    }

    public int decreaseTime() {
        --quantTime;
        return quantTime;
    }
}
