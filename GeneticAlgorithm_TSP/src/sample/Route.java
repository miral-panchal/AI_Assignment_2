package sample;

public class Route {
    private int distance;
    private int [] path;
    private double fitness = 0;

    Route(int tot_distance, int[] path) {
        this.distance = tot_distance;
        this.path = path;
    }

    public int get_distance() {return distance;}
    public int[] getPath() {return path;}
    public double getFitness() {return fitness;}

    public void setFitness(double fitness) { this.fitness = fitness; }

}
