public class Thread {
    private String description = "Поток ";
    private int quantTime;
    private int quant;
    public Thread(int threadNumb, int quantTime, int quant) {
        this.description += (threadNumb+1);
        this.quantTime = quantTime;
        this.quant = quant;

    }

    public int getQuantTime() {
        return quantTime;
    }
    public int quant() {
        return quant;
    }

    public String getDescription() {
        return description;
    }

    public int decreaseTime() {
        --quantTime;
        return quantTime;
    }
}
