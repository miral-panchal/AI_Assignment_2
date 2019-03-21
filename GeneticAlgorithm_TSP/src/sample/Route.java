package sample;

public class Route {
    private int distance;
    private int [] path;
    private int fitness = 0;

    Route(int tot_distance, int[] path) {
        this.distance = tot_distance;
        this.path = path;
    }

    public int get_distance() {return distance;}
    public int[] getPath() {return path;}
    public int getFitness() {return fitness;}

    public void setFitness(int fitness) { this.fitness = fitness; }

}
