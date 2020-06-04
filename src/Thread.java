public class Thread {
    private String description = "Поток ";
    private int quantTime;
    private int quant;
    private int currentTime;
    public Thread(int threadNumb, int quantTime, int quant) {
        this.description += (threadNumb+1);
        this.quantTime = quantTime;
        this.currentTime = quant;
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
    public int getCurrentTimeTh() {
        return currentTime;
    }
    public void decreaseCurrentTimeTh() {
        --currentTime;
    }
    public void restoreCurrentQuantTimeTh() {
        currentTime = quant;
    }
}
